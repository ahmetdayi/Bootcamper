package bim444.bootcamper.userlanguage;

import java.util.List;

public record CreateUserLanguageRequest(
        String userId,
        List<String> languageIdList
) {
}
