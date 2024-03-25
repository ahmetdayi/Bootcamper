package bim444.bootcamper.jsoup;

import bim444.bootcamper.exception.ConstantError;
import bim444.bootcamper.common.ConstantUrl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@RequiredArgsConstructor
@Slf4j
public class PatikaScrapeData {

    private final JSoupService jSoupService;

    public List<PatikaInfoResponse> scrapeLiveBootcamp() {
        List<PatikaInfoResponse> patikaInfoResponseList = new ArrayList<>();
        Boolean isDead = false;

        Document doc = jSoupService.connect(ConstantUrl.PATIKA_BOOTCAMPS_URL);
        log.info("patikaya baglanildi");

        List<String> activeBootcampLink =
                getBootcampLink(doc,"a.bootcampcurrentlink.w-inline-block.w-condition-invisible");
        log.info("aktif bootcamp linkleri getirildi");
        getPatikaInfoByLink(activeBootcampLink, patikaInfoResponseList, isDead);
        log.info("aktif bootcampler getirildi");
        return patikaInfoResponseList;
    }



    public List<PatikaInfoResponse> scrapeDeadBootcamp(){
        List<PatikaInfoResponse> patikaInfoResponseList = new ArrayList<>();
        Document doc = jSoupService.connect(ConstantUrl.PATIKA_BOOTCAMPS_URL);
        log.info("patikaya baglanildi");
        Boolean isDead = true;

        List<String> deadBootcampLinks = getBootcampLink(doc, "a.gecmisprogramcard.w-inline-block");
        log.info("gecmis bootcamp linkleri getirildi");

        getPatikaInfoByLink(deadBootcampLinks,patikaInfoResponseList,isDead);
        log.info("gecmis bootcampler getirildi");

        return patikaInfoResponseList;
    }

    private PatikaInfoResponse getInfoByMatcher(String bootcampUrl, Matcher matcher) {
        if (matcher.find()) {
            String programName = matcher.group(1);
            String startDate = matcher.group(2);
            String endDate = matcher.group(3);
            String location = matcher.group(4);
            String deadline = matcher.group(5);

            log.info("Matcher yardimiyla patika bilgileri alindi");

            return PatikaInfoResponse
                    .builder()
                    .id(UUID.randomUUID().toString())
                    .programName(programName)
                    .startDate(startDate)
                    .endDate(endDate)
                    .location(location)
                    .deadline(deadline)
                    .link(bootcampUrl)
                    .build();

        }
        log.error("matcher da bir hata olustu");
        return null;
    }
    private void getPatikaInfoByLink(List<String> activeBootcampLink,
                                     List<PatikaInfoResponse> patikaInfoResponseList,
                                     Boolean isDead) {
        for (String bootcampUrl : activeBootcampLink) {
            Document bootcampDoc = jSoupService.connect(bootcampUrl);
            log.info("patikada bootcamp linkine baglanildi");

            Elements bootcampDetailCardsDiv = bootcampDoc.select("div.bootcampdetailcardsdiv");

            String imgSrc = bootcampDetailCardsDiv.select("img").attr("src");
            Elements bootcampContent = bootcampDetailCardsDiv.select("div.bootcampcontent");
            Elements select = bootcampContent.select("div.iconandtextlistiemdiv");

            String bootcampLanguage = select.getFirst().text();
            log.info("patikada bootcamp programinin dili alindi.");

            Pattern pattern = Pattern.compile("(.+) (\\d{1,2}/\\d{1,2}/\\d{4}) - (\\d{1,2}/\\d{1,2}/\\d{4}) (.+) Deadline: (.+)");
            Matcher matcher = pattern.matcher(bootcampContent.text());
            PatikaInfoResponse infoByMatcher = getInfoByMatcher(bootcampUrl, matcher);

            if (infoByMatcher == null) {
                throw new JsoupScrapeErrorException(
                        String.format("%s %s",
                                ConstantError.JSOUP_SCRAPE_ERROR, ConstantUrl.PATIKA_BOOTCAMPS_URL));
            }

            PatikaInfoResponse patikaInfoResponse = PatikaInfoResponse
                    .builder()
                    .programName(infoByMatcher.programName())
                    .startDate(infoByMatcher.startDate())
                    .endDate(infoByMatcher.endDate())
                    .location(infoByMatcher.location())
                    .deadline(infoByMatcher.deadline())
                    .programLanguage(bootcampLanguage)
                    .img(imgSrc)
                    .link(bootcampUrl)
                    .isDead(isDead)
                    .build();
            log.info("bootcamp patikaInfoResponseList'e eklendi");
            patikaInfoResponseList.add(patikaInfoResponse);

        }
    }

    private List<String> getBootcampLink(Document doc,String aTagsClass) {
        Elements divListItem = doc.select("div[role=listitem]");
        Elements currentBootcampATags = divListItem
                .select(aTagsClass);
        log.info("bootcamp linkleri alindi");
        return currentBootcampATags
                .stream()
                .map(element -> ConstantUrl.PATIKA_BASE_URL + element.attr("href"))
                .toList();
    }
}
