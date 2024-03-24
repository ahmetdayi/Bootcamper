package bim444.bootcamper.coderspace;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/coderspace")
@RequiredArgsConstructor
public class CoderspaceController {

    private final CoderspaceService coderspaceService;

    @GetMapping("/getDatabaseScrapeBootcamp")
    public ResponseEntity<List<CoderspaceResponse>> getDatabaseScrapeBootcamp(){
        return new ResponseEntity<>(coderspaceService.getDatabaseScrapeBootcamp(), HttpStatus.OK);
    }

    @GetMapping("/getScrapeBootcamp")
    public ResponseEntity<List<CoderspaceResponse>> getScrapeBootcamp(){
        return new ResponseEntity<>(coderspaceService.getScrapeBootcamp(), HttpStatus.OK);
    }
}
