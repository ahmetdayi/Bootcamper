package bim444.bootcamper.basebootcamp.techcareer;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/techcareer")
@RequiredArgsConstructor
public class TechcareerController {
    private final TechcareerService techcareerService;

    @GetMapping("/getDatabaseScrapeBootcamp")
    public ResponseEntity<List<TechcareerResponse>> getDatabaseScrapeBootcamp(){
        return new ResponseEntity<>(techcareerService.getDatabaseScrapeBootcamp(), HttpStatus.OK);
    }

    @GetMapping("/getScrapeBootcamp")
    public ResponseEntity<List<TechcareerResponse>> getScrapeBootcamp(){
        return new ResponseEntity<>(techcareerService.getScrapeBootcamp(), HttpStatus.OK);
    }
}
