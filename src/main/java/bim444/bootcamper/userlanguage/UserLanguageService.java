package bim444.bootcamper.userlanguage;

import bim444.bootcamper.language.Language;
import bim444.bootcamper.language.LanguageService;
import bim444.bootcamper.user.User;
import bim444.bootcamper.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserLanguageService {

    private final UserLanguageRepository userLanguageRepository;

    private final UserLanguageConverter userLanguageConverter;

    private final UserService userService;

    private final LanguageService languageService;




    public void create(CreateUserLanguageRequest request) {
        User user = userService.findById(request.userId());
        List<Language> languageList = languageService.findByIdIn(request.languageIdList());
        List<UserLanguage> userLanguageList = languageList.stream()
                .map(language -> UserLanguage.builder()
                        .user(user)
                        .language(language)
                        .build()).toList();

        userLanguageRepository.saveAll(userLanguageList);
        log.info("UserLanguageler kaydedildi.");
    }

    public List<UserLanguageResponse> findByUserId(String userId) {
        List<UserLanguage> userLanguageList = userLanguageRepository.findByUser_Id(userId);
        log.info("user'a gore userlanguageler getirildi.");
        List<UserLanguageResponse> convert = userLanguageConverter.convert(userLanguageList);
        log.info("userLanguageler convert edildi.");
        return convert;

    }

    public List<UserLanguage> findByLanguage(Language language){
        return userLanguageRepository.findByLanguage(language);
    }
}
