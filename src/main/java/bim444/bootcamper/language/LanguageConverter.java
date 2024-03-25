package bim444.bootcamper.language;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LanguageConverter {

    public LanguageResponse convert(Language from){
        return new LanguageResponse(from.getId(), from.getName());
    }

    public List<LanguageResponse> convert(List<Language> fromlist){
        return fromlist.stream()
                .map(from-> new LanguageResponse(from.getId(), from.getName()))
                .toList();
    }
}
