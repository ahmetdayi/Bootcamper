package bim444.bootcamper.userlanguage;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record CreateUserLanguageRequest(
        @NotBlank String userId,
        List<String> languageIdList
) {
}
