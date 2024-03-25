package bim444.bootcamper.basebootcamp.patika;

import bim444.bootcamper.language.LanguageResponse;
import lombok.Builder;

@Builder
public record PatikaResponse(
        String id,
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
