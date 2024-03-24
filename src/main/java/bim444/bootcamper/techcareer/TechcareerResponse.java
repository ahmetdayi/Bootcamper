package bim444.bootcamper.techcareer;

import lombok.Builder;

import java.util.UUID;

@Builder
public record TechcareerResponse(
        UUID id,
        String name,
        String imgUrl,
        String link,
        String deadline,
        String eventType
) {
}
