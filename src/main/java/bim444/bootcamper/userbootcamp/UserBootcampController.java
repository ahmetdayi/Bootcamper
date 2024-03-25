package bim444.bootcamper.userbootcamp;

import bim444.bootcamper.basebootcamp.coderspace.CoderspaceResponse;
import bim444.bootcamper.basebootcamp.patika.PatikaResponse;
import bim444.bootcamper.basebootcamp.techcareer.TechcareerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/userbootcamp")
public class UserBootcampController {

    private final UserBootcampService userBootcampService;

    @PostMapping("/create")
    public ResponseEntity<Void> create(@RequestBody CreateUserBootcampRequest request) {
        userBootcampService.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/findByUserId/{userId}")
    public ResponseEntity<List<UserBootcampResponse>> findByUserId(@PathVariable String userId) {
        return new ResponseEntity<>(userBootcampService.findByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/findPatikasByUserId/{userId}")
    public ResponseEntity<List<PatikaResponse>> findPatikasByUserId(@PathVariable String userId) {
        return new ResponseEntity<>(userBootcampService.findPatikasByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/findTechcareerByUserId/{userId}")
    public ResponseEntity<List<TechcareerResponse>> findTechcareerByUserId(@PathVariable String userId) {
        return new ResponseEntity<>(userBootcampService.findTechcareerByUserId(userId), HttpStatus.OK);
    }

    @GetMapping("/findCoderspaceByUserId/{userId}")
    public ResponseEntity<List<CoderspaceResponse>> findCoderspaceByUserId(@PathVariable String userId) {
        return new ResponseEntity<>(userBootcampService.findCoderspaceByUserId(userId), HttpStatus.OK);
    }
}
