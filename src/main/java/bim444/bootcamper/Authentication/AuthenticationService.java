package bim444.bootcamper.Authentication;




import bim444.bootcamper.security.JwtService;
import bim444.bootcamper.security.SecurityUserService;
import bim444.bootcamper.token.Token;
import bim444.bootcamper.token.TokenRepository;
import bim444.bootcamper.token.TokenType;
import bim444.bootcamper.user.User;
import bim444.bootcamper.user.UserConverter;
import bim444.bootcamper.user.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserService userService;
    private final SecurityUserService securityUserService;
    private final TokenRepository tokenRepository;
    private final JwtService jwtService;
    private final UserConverter userConverter;
    private final AuthenticationManager authenticationManager;


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        request.email(), request.password()));

        UserDetails userDetails = securityUserService.loadUserByUsername(request.email());
        User user = userService.findByEmail(request.email());
        var jwtToken = jwtService.generateToken(userDetails);
        var refreshToken = jwtService.generateRefreshToken(userDetails);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .userResponse(userConverter.convert(user))
                .accessToken(jwtToken)
                .refreshToken(refreshToken)
                .build();
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    private void revokeAllUserTokens(User user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String refreshToken;
        final String userEmail;
        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return;
        }
        refreshToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(refreshToken);
        if (userEmail != null) {
            var user = userService.findByEmail(userEmail);
            var securityUser = securityUserService.loadUserByUsername(userEmail);
            List<Token> validUserTokens = tokenRepository.findAllValidTokenByUser(userService.findByEmail(userEmail).getId());

            if (jwtService.isTokenValid(refreshToken, securityUser) && !validUserTokens.isEmpty()) {
                var accessToken = jwtService.generateToken(securityUser);
                revokeAllUserTokens(user);
                saveUserToken(user, accessToken);
                var authResponse = AuthenticationResponse.builder()
                        .userResponse(userConverter.convert(user))
                        .accessToken(accessToken)
                        .refreshToken(refreshToken)
                        .build();
                new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
            }
        }
    }
}