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

    public void saveAll(List<Language> language){
        List<Language> list = language.stream().filter(language1 -> findByName(language1.getName()) == null).toList();
        languageRepository.saveAll(list);
        log.info("language databaseye kaydedildi");
    }

    public Language findByName(String name){
        return languageRepository.findByName(name).orElse(null);
    }
}
