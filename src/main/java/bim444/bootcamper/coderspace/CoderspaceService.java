package bim444.bootcamper.coderspace;

import bim444.bootcamper.jsoup.CoderspaceInfoResponse;
import bim444.bootcamper.jsoup.CoderspaceScrapeData;
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
        List<Coderspace> databaseConflictData = coderspaceList
                .stream()
                .map(coderspace -> findByName(coderspace.getName()) )
                .toList();
        List<Coderspace> newData = coderspaceRepository.saveAll(filterdTechcareerList);
        conflictData.forEach(coderspace ->{
            databaseConflictData.forEach(coderspace1 -> {
                if (coderspace.getIsDead() && !coderspace1.getIsDead()){
                    coderspace1.setIsDead(true);
                    coderspaceRepository.save(coderspace1);
                }
            });
        } );
        newList.addAll(newData);
        newList.addAll(databaseConflictData);
        log.info("coderspace database e kaydedildi");
        return newList;
    }


    private Coderspace findByName(String name){
        return coderspaceRepository.findByName(name).orElse(null);
    }
}
