package bim444.bootcamper.language;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@Order(0)
public class LanguageService {
    private final LanguageRepository languageRepository;
    private final LanguageConverter languageConverter;

    public void saveAll(List<Language> language){
        List<Language> list = language.stream().filter(language1 -> findByName(language1.getName()) == null).toList();
        languageRepository.saveAll(list);
        log.info("language databaseye kaydedildi");
    }

    public List<LanguageResponse> findAll(){
        List<Language> languageList = languageRepository.findAll();
        log.info("Language listesi getirildi.");
        List<LanguageResponse> convert = languageConverter.convert(languageList);
        log.info("Language listesi convert edildi.");
        return convert;
    }

    private Language findByName(String name){
        Language language = languageRepository.findByName(name).orElse(null);
        log.info("Language name ile getirildi.");
        return language;
    }

    public List<Language> findByIdIn(List<String> idList){
        List<Language> languageList = languageRepository.findByIdIn(idList);
        log.info("Languageler id listesi ile getirildi.");
        return languageList;
    }
}
