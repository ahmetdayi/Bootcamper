package bim444.bootcamper.authentication;

import bim444.bootcamper.user.UserResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;

@Builder
public record AuthenticationResponse(
        UserResponse userResponse,
        @JsonProperty("access_token")
        String accessToken,
        @JsonProperty("refresh_token")
        String refreshToken

) {
}
