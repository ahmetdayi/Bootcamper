package bim444.bootcamper.language;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/language")
@RequiredArgsConstructor
public class LanguageController {

    private final LanguageService languageService;

    @GetMapping("/findall")
    public ResponseEntity<List<LanguageResponse>> findAll(){
        return new ResponseEntity<>(languageService.findAll(), HttpStatus.OK);
    }
}
