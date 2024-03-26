package bim444.bootcamper.basebootcamp.coderspace;

import bim444.bootcamper.basebootcamp.BaseBootcamp;
import bim444.bootcamper.jsoup.CoderspaceInfoResponse;
import bim444.bootcamper.jsoup.CoderspaceScrapeData;
import bim444.bootcamper.mail.MailService;
import bim444.bootcamper.mail.SendMailRequest;
import bim444.bootcamper.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CoderspaceService {

    private final CoderspaceRepository coderspaceRepository;
    private final CoderspaceConverter coderspaceConverter;
    private final CoderspaceScrapeData coderspaceScrapeData;
    private final UserService userService;
    private final MailService mailService;

    public List<CoderspaceResponse> getDatabaseScrapeBootcamp(){
        convertAndSaveBootcamp();
        return coderspaceConverter.convert(coderspaceRepository.findAll());
    }
    public List<CoderspaceResponse> getScrapeBootcamp(){
        List<Coderspace> coderspaceList = convertAndSaveBootcamp();
        return coderspaceConverter.convert(coderspaceList);
    }

    private List<Coderspace> convertAndSaveBootcamp() {
        List<Coderspace> newList = new ArrayList<>();
        List<Coderspace> newBootcampList = new ArrayList<>();

        List<CoderspaceInfoResponse> coderspaceInfoResponseList = coderspaceScrapeData.scrapeBootcamp();
        List<Coderspace> coderspaceList = coderspaceConverter.convertResponse(coderspaceInfoResponseList);
        List<Coderspace> filterdTechcareerList = coderspaceList
                .stream()
                .filter(coderspace -> findByName(coderspace.getName()) == null)
                .toList();

        List<Coderspace> conflictData = coderspaceList
                .stream()
                .filter(coderspace -> findByName(coderspace.getName()) != null)
                .toList();
        List<Coderspace> databaseConflictData = conflictData
                .stream()
                .map(coderspace -> findByName(coderspace.getName()) )
                .toList();

        conflictData.forEach(coderspace ->{
            databaseConflictData.forEach(coderspace1 -> {
                if (coderspace.getIsDead() && !coderspace1.getIsDead()){
                    coderspace1.setIsDead(true);
                    coderspaceRepository.save(coderspace1);
                }
            });
        } );
        if (!filterdTechcareerList.isEmpty()){
            newBootcampList = coderspaceRepository.saveAll(filterdTechcareerList);
            List<Coderspace> finalNewBootcampList = newBootcampList;
            userService.findAll().forEach(user -> {
                mailService.sendMail(new SendMailRequest(user.getEmail(),getCoderspacesUrl(finalNewBootcampList).toString(),"New Bootcamp"));
            });
        }
        newList.addAll(newBootcampList);
        newList.addAll(databaseConflictData);
        log.info("coderspace database e kaydedildi");
        return newList;
    }
    private List<String> getCoderspacesUrl(List<Coderspace> coderspaceList){
        return coderspaceList.stream()
                .map(BaseBootcamp::getLink).toList();
    }

    private Coderspace findByName(String name){
        return coderspaceRepository.findByName(name).orElse(null);
    }
}
