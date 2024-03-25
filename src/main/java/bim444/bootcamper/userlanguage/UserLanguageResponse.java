package bim444.bootcamper.userlanguage;

import bim444.bootcamper.language.LanguageResponse;
import bim444.bootcamper.user.UserResponse;

public record UserLanguageResponse(
        String id,
        UserResponse user,
        LanguageResponse language
) {
}
