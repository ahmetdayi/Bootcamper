package bim444.bootcamper.user;

import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserResponse convert(User from){
        return new UserResponse(from.getId(), from.getName(), from.getEmail());
    }
}
