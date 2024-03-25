package bim444.bootcamper.basebootcamp.techcareer;

import bim444.bootcamper.jsoup.TechcareerInfoResponse;
import bim444.bootcamper.jsoup.TechcareerScrapeData;
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

        newList.addAll(newData);
        newList.addAll(getDatabaseConflictData);
        log.info("techcareer database e kaydedildi");
        return newList;
    }

    private Techcareer findByName(String name){
        return techcareerRepository.findByName(name).orElse(null);
    }
}
