package bim444.bootcamper.jsoup;

import lombok.Builder;

import java.util.UUID;

@Builder
public record PatikaInfoResponse(
        UUID id,
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
