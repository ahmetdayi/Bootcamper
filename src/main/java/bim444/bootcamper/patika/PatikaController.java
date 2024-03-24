package bim444.bootcamper.patika;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/patika")
@RequiredArgsConstructor
public class PatikaController {

    private final PatikaService patikaService;

    @GetMapping("/getDatabaseBootcamp")
    public ResponseEntity<List<PatikaResponse>> getDatabaseBootcamp(){
        return new ResponseEntity<>(patikaService.getDatabaseBootcamp(), HttpStatus.OK);
    }

    @GetMapping("/scrapeLiveBootcamp")
    public ResponseEntity<List<PatikaResponse>> scrapeLiveBootcamp(){
        return new ResponseEntity<>(patikaService.scrapeLiveBootcamp(), HttpStatus.OK);
    }

    @GetMapping("/scrapeDeadBootcamp")
    public ResponseEntity<List<PatikaResponse>> scrapeDeadBootcamp(){
        return new ResponseEntity<>(patikaService.scrapeDeadBootcamp(), HttpStatus.OK);
    }
}
