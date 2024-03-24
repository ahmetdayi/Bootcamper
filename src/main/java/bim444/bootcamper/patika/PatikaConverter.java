package bim444.bootcamper.patika;

import bim444.bootcamper.jsoup.PatikaInfoResponse;
import bim444.bootcamper.language.Language;
import bim444.bootcamper.language.LanguageConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Order(1)
public class PatikaConverter {


    private final LanguageConverter languageConverter;

    public List<Patika> convertResponse(List<PatikaInfoResponse> fromlist) {
        return fromlist
                .stream()
                .map(from ->
                        Patika
                                .builder()
                                .id(from.id())
                                .name(from.programName())
                                .location(from.location())
                                .startDate(from.startDate())
                                .endDate(from.endDate())
                                .imgUrl(from.img())
                                .deadline(from.deadline())
                                .language(new Language(from.programLanguage()))
                                .link(from.link())
                                .isDead(from.isDead())
                                .build()
                )
                .collect(Collectors.toList());
    }
    public List<PatikaResponse> convert(List<Patika> fromlist) {
        return fromlist
                .stream()
                .map(from ->
                        PatikaResponse
                                .builder()
                                .id(from.getId())
                                .name(from.getName())
                                .location(from.getLocation())
                                .startDate(from.getLocation())
                                .endDate(from.getEndDate())
                                .imgUrl(from.getImgUrl())
                                .deadline(from.getDeadline())
                                .language(languageConverter.convert(from.getLanguage()))
                                .link(from.getLink())
                                .isDead(from.getIsDead())
                                .build()
                )
                .collect(Collectors.toList());
    }
}
