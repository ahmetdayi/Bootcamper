package bim444.bootcamper.coderspace;

import bim444.bootcamper.jsoup.CoderspaceInfoResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CoderspaceConverter {

    public List<Coderspace> convertResponse(List<CoderspaceInfoResponse> fromlist){
        return fromlist.stream()
                .map(from-> Coderspace.builder()
                        .id(from.id())
                        .name(from.name())
                        .imgUrl(from.imgUrl())
                        .deadline(from.deadline())
                        .description(from.description())
                        .status(from.status())
                        .isDead(from.isDead())
                        .link(from.link())
                        .eventType(from.eventType())
                        .build()).collect(Collectors.toList());
    }

    public List<CoderspaceResponse> convert(List<Coderspace> fromlist){
        return fromlist.stream()
                .map(from-> CoderspaceResponse.builder()
                        .id(from.getId())
                        .name(from.getName())
                        .imgUrl(from.getImgUrl())
                        .deadline(from.getDeadline())
                        .description(from.getDescription())
                        .status(from.getStatus())
                        .isDead(from.getIsDead())
                        .link(from.getLink())
                        .eventType(from.getEventType())
                        .build())
                .collect(Collectors.toList());
    }
}
