package bim444.bootcamper.user;

import bim444.bootcamper.exception.AlreadyExistException;
import bim444.bootcamper.exception.ConstantError;
import bim444.bootcamper.exception.NotExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final BCryptPasswordEncoder encoder;

    public void create(CreateUserRequest request){
        if (userRepository.findByEmail(request.email()).isPresent()){
            throw new AlreadyExistException(ConstantError.ALREADY_EXIST);
        }
        User user = User.builder()
                .email(request.email())
                .name(request.name())
                .role(Role.USER)
                .password(encoder.encode(request.password()))
                .build();
        userRepository.save(user);
        log.info("user kaydedildi");
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new NotExistException(ConstantError.NOT_EXIST));
    }
    public UserResponse getById(String id){
        User user = findById(id);
        UserResponse convert = userConverter.convert(user);
        log.info("User convert edildi.");
        return convert;
    }

    public User findById(String id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotExistException(ConstantError.NOT_EXIST));
        log.info("User id ile getirildi.");
        return user;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
