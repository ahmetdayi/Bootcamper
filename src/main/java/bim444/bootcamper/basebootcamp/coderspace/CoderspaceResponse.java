package bim444.bootcamper.basebootcamp.coderspace;

import lombok.Builder;

@Builder
public record CoderspaceResponse(
        String id,
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
