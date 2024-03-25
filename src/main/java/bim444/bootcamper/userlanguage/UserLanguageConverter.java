package bim444.bootcamper.userlanguage;

import bim444.bootcamper.language.LanguageConverter;
import bim444.bootcamper.user.UserConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserLanguageConverter {
    private final UserConverter userConverter;

    private final LanguageConverter languageConverter;

    public List<UserLanguageResponse> convert(List<UserLanguage> fromlist){
        return fromlist.stream()
                .map(from-> new UserLanguageResponse(
                        from.getId(),
                        userConverter.convert(from.getUser()),
                        languageConverter.convert(from.getLanguage())))
                .toList();
    }
}
