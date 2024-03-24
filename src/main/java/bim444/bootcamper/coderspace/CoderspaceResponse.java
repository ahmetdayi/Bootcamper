package bim444.bootcamper.coderspace;

import lombok.Builder;

import java.util.UUID;

@Builder
public record CoderspaceResponse(
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
