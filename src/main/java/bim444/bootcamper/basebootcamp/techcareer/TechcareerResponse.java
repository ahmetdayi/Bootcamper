package bim444.bootcamper.basebootcamp.techcareer;

import lombok.Builder;

@Builder
public record TechcareerResponse(
        String id,
        String name,
        String imgUrl,
        String link,
        String deadline,
        String eventType
) {
}
