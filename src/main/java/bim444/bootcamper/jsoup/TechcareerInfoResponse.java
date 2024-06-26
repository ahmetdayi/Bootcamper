package bim444.bootcamper.jsoup;

import lombok.Builder;

@Builder
public record TechcareerInfoResponse(
        String id,
        String name,
        String imgUrl,
        String link,
        String deadline,
        String eventType
) {
}
