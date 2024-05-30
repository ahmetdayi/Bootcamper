package bim444.bootcamper.userbootcamp;

import bim444.bootcamper.basebootcamp.coderspace.Coderspace;
import bim444.bootcamper.basebootcamp.coderspace.CoderspaceConverter;
import bim444.bootcamper.basebootcamp.coderspace.CoderspaceResponse;
import bim444.bootcamper.basebootcamp.BaseBootcamp;
import bim444.bootcamper.basebootcamp.BaseBootcampService;
import bim444.bootcamper.basebootcamp.patika.Patika;
import bim444.bootcamper.basebootcamp.patika.PatikaConverter;
import bim444.bootcamper.basebootcamp.patika.PatikaResponse;
import bim444.bootcamper.basebootcamp.techcareer.Techcareer;
import bim444.bootcamper.basebootcamp.techcareer.TechcareerConverter;
import bim444.bootcamper.basebootcamp.techcareer.TechcareerResponse;
import bim444.bootcamper.exception.AlreadyExistException;
import bim444.bootcamper.user.User;
import bim444.bootcamper.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserBootcampService {

    private final UserBootcampRepository userBootcampRepository;
    private final UserBootcampConverter userBootcampConverter;
    private final UserService userService;
    private final PatikaConverter patikaConverter;
    private final TechcareerConverter techcareerConverter;
    private final BaseBootcampService baseBootcampService;
    private final CoderspaceConverter coderspaceConverter;

    public void create(CreateUserBootcampRequest request){
        if (userBootcampRepository.findByUser_IdAndBaseBootcamp_Id(request.userId(), request.baseBootcampId())!=null){
            throw new AlreadyExistException("User bootcamp already exists");

        }

        User user = userService.findById(request.userId());
        BaseBootcamp baseBootcamp = baseBootcampService.findById(request.baseBootcampId());
        UserBootcamp userBootcamp = UserBootcamp.builder()
                .user(user)
                .baseBootcamp(baseBootcamp)
                .build();
        userBootcampRepository.save(userBootcamp);
        log.info("UserBootcamp kaydedildi.");
    }

    public List<UserBootcampResponse> findByUserId(String userId){
        List<UserBootcamp> userBootcampList = userBootcampRepository.findByUser_Id(userId);
        log.info("user'a gore userbootcamp getirildi.");
        List<UserBootcampResponse> convert = userBootcampConverter.convert(userBootcampList);
        log.info("userbootcampler convert edildi.");
        return convert;
    }
    public List<PatikaResponse> findPatikasByUserId(String userId){
        List<Patika> patikaList = userBootcampRepository.findPatikasByUserId(userId);
        log.info("user'a gore patikalar getirildi.");
        List<PatikaResponse> convert = patikaConverter.convert(patikaList);
        log.info("patikalar convert edildi.");
        return convert;
    }
    public List<TechcareerResponse> findTechcareerByUserId(String userId){
        List<Techcareer> techcareerList = userBootcampRepository.findTechcareersByUserId(userId);
        log.info("user'a gore techcareerList getirildi.");
        List<TechcareerResponse> convert = techcareerConverter.convert(techcareerList);
        log.info("TechcareerResponseler convert edildi.");
        return convert;
    }
    public List<CoderspaceResponse> findCoderspaceByUserId(String userId){
        List<Coderspace> coderspaceList = userBootcampRepository.findCoderspaceByUserId(userId);
        log.info("user'a gore coderspaceList getirildi.");
        List<CoderspaceResponse> convert = coderspaceConverter.convert(coderspaceList);
        log.info("CoderspaceResponseler convert edildi.");
        return convert;
    }

}
