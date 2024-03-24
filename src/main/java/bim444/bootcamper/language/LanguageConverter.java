package bim444.bootcamper.language;

import org.springframework.stereotype.Component;

@Component
public class LanguageConverter {

    public LanguageResponse convert(Language from){
        return new LanguageResponse(from.getId(), from.getName());
    }
}
