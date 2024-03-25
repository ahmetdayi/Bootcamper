package bim444.bootcamper.userlanguage;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/userlanguage")
@RequiredArgsConstructor
public class UserLanguageController {

    private final UserLanguageService languageService;


    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody CreateUserLanguageRequest request){
        languageService.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/findByUserId/{userId}")
    public ResponseEntity<List<UserLanguageResponse>> findByUserId(@PathVariable String userId){
        return new ResponseEntity<>(languageService.findByUserId(userId),HttpStatus.CREATED);
    }
}
