package bim444.bootcamper.patika;

import bim444.bootcamper.language.LanguageResponse;
import lombok.Builder;

import java.util.UUID;

@Builder
public record PatikaResponse(
        UUID id,
        String name,
        String startDate,
        String endDate,
        String location,
        LanguageResponse language,
        String deadline,
        String imgUrl,
        String link,
        Boolean isDead
) {
}
