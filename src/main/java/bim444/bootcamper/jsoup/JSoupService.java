package bim444.bootcamper.jsoup;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class JSoupService {

    public Document connect(String url){
        try {
            log.info( "JSoup baglandi");
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            log.error(e + "JSoup Baglanti sorunu");
            throw new RuntimeException(e + "JSoup Baglanti sorunu");

        }
    }

    public Document connectWithUserAgent(String url,String userAgent){
        try {
            log.info( "JSoup user agent ile baglandi");
            return Jsoup.connect(url).userAgent(userAgent).get();
        } catch (IOException e) {
            log.error(e + "JSoup user agent Baglanti sorunu");
            throw new RuntimeException(e + "JSoup user agent Baglanti sorunu");

        }
    }

}
