package bim444.bootcamper;

import bim444.bootcamper.coderspace.CoderspaceService;
import bim444.bootcamper.patika.PatikaService;
import bim444.bootcamper.techcareer.TechcareerService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootcamperApplication implements CommandLineRunner {

	private final PatikaService patikaService;
	private final CoderspaceService coderspaceService;
	private final TechcareerService techcareerService;

    public BootcamperApplication(PatikaService patikaService, CoderspaceService coderspaceService, TechcareerService techcareerService) {
        this.patikaService = patikaService;
        this.coderspaceService = coderspaceService;
        this.techcareerService = techcareerService;
    }

    public static void main(String[] args) {
		SpringApplication.run(BootcamperApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
//		 patikaScrapeData.scrapeLiveBootcamp().forEach(System.out::println);
//		 patikaScrapeData.scrapeDeadBootcamp().forEach(System.out::println);
//		System.out.println("aaaaaaaaaaaaaaaaaa");
//		patikaService.scrapeDeadBootcamp().forEach(System.out::println);
//		System.out.println("aaaaaaaaaaaaaaaaaa");
//		patikaService.scrapeLiveBootcamp().forEach(System.out::println);
//		System.out.println("aaaaaaaaaaaaaaaaaa");
//		patikaService.getDatabaseBootcamp().forEach(System.out::println);
//		System.out.println("aaaaaaaaaaaaaaaaaa");
//
//		coderspaceService.getDatabaseScrapeBootcamp().forEach(System.out::println);
//		System.out.println("aaaaaaaaaaaaaaaaaa");
//		coderspaceService.getScrapeBootcamp().forEach(System.out::println);
//		System.out.println("aaaaaaaaaaaaaaaaaa");
//		techcareerService.getDatabaseScrapeBootcamp().forEach(System.out::println);
//		System.out.println("aaaaaaaaaaaaaaaaaa");
//		techcareerService.getScrapeBootcamp().forEach(System.out::println);
	}
}
