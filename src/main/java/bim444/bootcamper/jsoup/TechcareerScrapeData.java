package bim444.bootcamper.jsoup;

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
public class TechcareerScrapeData {
    private final JSoupService jSoupService;

    public List<TechcareerInfoResponse> scrapeBootcamp() {
        List<TechcareerInfoResponse> techcareerInfoResponseList = new ArrayList<>();

        Document doc = jSoupService
                .connectWithUserAgent(ConstantUrl.TECHCAREER_BOOTCAMPS_URL, ConstantUrl.TECHCAREER_USER_AGENT);
        log.info("techcareer icin jsoupa baglanildi.");
        Elements bootcampDetailCardsDiv = doc.select("div.MuiBox-root.css-9s7nl0");
        Elements names = bootcampDetailCardsDiv.select("h3.MuiTypography-root.MuiTypography-h6.css-r0m1bi");
        Elements linkspath = doc.select("div.MuiGrid-root.MuiGrid-item.MuiGrid-grid-xs-12.MuiGrid-grid-md-4.css-2c969z");
        Elements links1 = linkspath.select("a.MuiTypography-root.MuiTypography-inherit.MuiLink-root.MuiLink-underlineNone.css-adfut0");

        Elements deadlines = bootcampDetailCardsDiv.select("div.MuiTypography-root.MuiTypography-subtitle2.css-l8rqx2");
        Elements eventTypes = bootcampDetailCardsDiv.select("p.MuiTypography-root.MuiTypography-body2.css-856g1e");

        Elements imgUrls = bootcampDetailCardsDiv.select("img[data-test=single-event-image]");

        for (int i = 0; i < imgUrls.size(); i++) {
            String imgSrc = imgUrls.get(i).attr("src");
            String imageUrl = imgSrc.split(",")[0].trim().split(" ")[0];
            TechcareerInfoResponse techcareerInfoResponse = TechcareerInfoResponse
                    .builder()
                    .id(UUID.randomUUID().toString())
                    .name(names.get(i).text())
                    .link(ConstantUrl.TECHCAREER_BOOTCAMPS_URL + links1.get(i).attr("href"))
                    .deadline(deadlines.get(i).text())
                    .eventType(eventTypes.get(i).text())
                    .imgUrl("https://www.techcareer.net" + imageUrl)
                    .build();
            log.info("TechcareerInfoResponse olusturuldu.");
            techcareerInfoResponseList.add(techcareerInfoResponse);
        }
        return techcareerInfoResponseList;
    }

    }

