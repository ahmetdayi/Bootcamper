package bim444.bootcamper.jsoup;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CoderspaceInfoResponse(
        UUID id,
        String name,
        String imgUrl,
        String link,
        String deadline,
        String description,
        String status,
        String eventType,
        Boolean isDead

) {
}
