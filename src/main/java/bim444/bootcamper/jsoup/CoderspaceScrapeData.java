package bim444.bootcamper.jsoup;

import bim444.bootcamper.coderspace.CoderspaceStatus;
import bim444.bootcamper.common.ConstantUrl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class CoderspaceScrapeData {

    private final JSoupService jSoupService;

    public List<CoderspaceInfoResponse> scrapeBootcamp() {
        List<CoderspaceInfoResponse> coderspaceInfoResponseList = new ArrayList<>();
        Document doc = jSoupService.connect(ConstantUrl.CODERSPACE_BOOTCAMPS_URL);
        log.info("jsoup Coderspace'e baglandi");

        Elements bootcampDetailCardsDiv = doc.select("div.event-card");
        Elements links = bootcampDetailCardsDiv.select("a.event-card-image");
        Elements imgUrls = links.select("img");
        Elements names = bootcampDetailCardsDiv.select("h5");

        Elements descriptions = bootcampDetailCardsDiv.select("p");
        Elements eventTypes = bootcampDetailCardsDiv.select("span.event-card-type");
        Elements deadLinesPath = bootcampDetailCardsDiv.select("ul.event-card-info");

        Elements status = bootcampDetailCardsDiv.select("a.primary-button.primary-button--big");
        Elements applicationStatus = status.select("span");

        for (int i = 0; i < names.size(); i++) {
            String scrapeStatus = applicationStatus.get(i).text();
            Boolean isDead = CoderspaceStatus.BASVURULAR_TAMAMLANDI.equalsIgnoreCase(scrapeStatus);

            CoderspaceInfoResponse coderspaceInfoResponse = CoderspaceInfoResponse
                    .builder()
                    .id(UUID.randomUUID())
                    .name(names.get(i).text())
                    .link(ConstantUrl.CODERSPACE_BASE_URL + links.get(i).attr("href"))
                    .description(descriptions.get(i).text())
                    .eventType(eventTypes.get(i).text())
                    .imgUrl(imgUrls.get(i).attr("src"))
                    .deadline(deadLinesPath.get(i).text())
                    .status(scrapeStatus)
                    .isDead(isDead)
                    .build();
            log.info("CoderspaceInfoResponse olusturuldu");
            coderspaceInfoResponseList.add(coderspaceInfoResponse);

        }
        return coderspaceInfoResponseList;
    }

}
