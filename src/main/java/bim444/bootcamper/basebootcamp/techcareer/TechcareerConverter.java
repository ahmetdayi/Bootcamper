package bim444.bootcamper.basebootcamp.techcareer;

import bim444.bootcamper.jsoup.TechcareerInfoResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TechcareerConverter {

    public List<Techcareer> convertResponse(List<TechcareerInfoResponse> fromlist) {
        return fromlist
                .stream()
                .map(from ->
                        Techcareer
                                .builder()
                                .id(from.id())
                                .name(from.name())
                                .imgUrl(from.imgUrl())
                                .deadline(from.deadline())
                                .link(from.link())
                                .eventType(from.eventType())
                                .build()
                )
                .collect(Collectors.toList());
    }

    public List<TechcareerResponse> convert(List<Techcareer> fromlist) {
        return fromlist
                .stream()
                .map(from ->
                        TechcareerResponse.builder()
                                .id(from.getId())
                                .name(from.getName())
                                .imgUrl(from.getImgUrl())
                                .deadline(from.getDeadline())
                                .link(from.getLink())
                                .eventType(from.getEventType())
                                .build()
                )
                .collect(Collectors.toList());
    }

}
