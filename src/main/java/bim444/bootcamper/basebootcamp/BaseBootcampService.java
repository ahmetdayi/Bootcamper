package bim444.bootcamper.basebootcamp;

import bim444.bootcamper.exception.ConstantError;
import bim444.bootcamper.exception.NotExistException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BaseBootcampService {

    private final BaseBootcampRepository baseBootcampRepository;

    public BaseBootcamp findById(String id){
        return baseBootcampRepository.findById(id)
                .orElseThrow(() -> new NotExistException(ConstantError.NOT_EXIST));
    }
}
