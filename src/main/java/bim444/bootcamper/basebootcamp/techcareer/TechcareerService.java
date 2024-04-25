package bim444.bootcamper.basebootcamp.techcareer;

import bim444.bootcamper.basebootcamp.BaseBootcamp;
import bim444.bootcamper.jsoup.TechcareerInfoResponse;
import bim444.bootcamper.jsoup.TechcareerScrapeData;
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
public class TechcareerService {

    private final TechcareerRepository techcareerRepository;
    private final TechcareerScrapeData techcareerScrapeData;
    private final TechcareerConverter techcareerConverter;
    private final UserService userService;
    private final MailService mailService;

    public List<TechcareerResponse> getDatabaseScrapeBootcamp(){
        convertAndSaveBootcamp();
        return techcareerConverter.convert(techcareerRepository.findAll());
    }



    public List<TechcareerResponse> getScrapeBootcamp(){
        List<Techcareer> techcareerList = convertAndSaveBootcamp();
        return techcareerConverter.convert(techcareerList);
    }

    private List<Techcareer> convertAndSaveBootcamp() {
        List<Techcareer> newList = new ArrayList<>();
        List<Techcareer> newBootcampList = new ArrayList<>();
        List<TechcareerInfoResponse> techcareerInfoResponseList = techcareerScrapeData.scrapeBootcamp();
        List<Techcareer> techcareerList = techcareerConverter.convertResponse(techcareerInfoResponseList);
        List<Techcareer> filterdTechcareerList = techcareerList
                .stream()
                .filter(techcareer -> findByName(techcareer.getName()) == null)
                .toList();
        List<Techcareer> conflictData = techcareerList
                .stream()
                .filter(techcareer -> findByName(techcareer.getName()) != null)
                .toList();
        List<Techcareer> getDatabaseConflictData = conflictData.stream()
                .map(techcareer -> findByName(techcareer.getName()) )
                .toList();
        List<Techcareer> newData = techcareerRepository.saveAll(filterdTechcareerList);
        //burda databasede olan datalari databaseden cekip koyduk
        //databasede olamayan datalari kaydedip aldik ve yeni listeye ekleyip donduk
        //bu sayede gelen tum datalar database e kaydedildi ve idli bir sekilde dondu
        if (!filterdTechcareerList.isEmpty()){
            newBootcampList = techcareerRepository.saveAll(filterdTechcareerList);
            List<Techcareer> finalNewBootcampList = newBootcampList;
            userService.findAll().forEach(user -> {
                mailService.sendMail(new SendMailRequest(user.getEmail(),getTechcareerUrl(finalNewBootcampList).toString(),"New Bootcamp"));
            });
        }
        newList.addAll(newData);
        newList.addAll(getDatabaseConflictData);
        log.info("techcareer database e kaydedildi");
        return newList;
    }
    private List<String> getTechcareerUrl(List<Techcareer> techcareerList){
        return techcareerList.stream()
                .map(BaseBootcamp::getLink).toList();
    }

    private Techcareer findByName(String name){
        return techcareerRepository.findByName(name).stream().findFirst().orElse(null);

    }

}
