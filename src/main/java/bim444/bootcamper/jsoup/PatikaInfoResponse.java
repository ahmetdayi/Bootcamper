package bim444.bootcamper.jsoup;

import lombok.Builder;

@Builder
public record PatikaInfoResponse(
        String id,
        String programName,
        String startDate,
        String endDate,
        String location,
        String programLanguage,
        String deadline,
        String img,
        String link,
        Boolean isDead
) {
}
