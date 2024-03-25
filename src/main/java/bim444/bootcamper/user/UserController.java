package bim444.bootcamper.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody CreateUserRequest request){
        userService.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<UserResponse> create(@PathVariable String id){
        return new ResponseEntity<>(userService.getById(id),HttpStatus.CREATED);
    }
}
