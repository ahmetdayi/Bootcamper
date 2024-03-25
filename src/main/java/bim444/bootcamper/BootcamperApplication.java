package bim444.bootcamper;

import bim444.bootcamper.basebootcamp.coderspace.CoderspaceService;
import bim444.bootcamper.basebootcamp.patika.PatikaService;
import bim444.bootcamper.basebootcamp.techcareer.TechcareerService;
import bim444.bootcamper.userbootcamp.UserBootcampRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootcamperApplication implements CommandLineRunner {

	private final PatikaService patikaService;
	private final CoderspaceService coderspaceService;
	private final TechcareerService techcareerService;
	private final UserBootcampRepository userBootcampRepository;

    public BootcamperApplication(PatikaService patikaService, CoderspaceService coderspaceService, TechcareerService techcareerService, UserBootcampRepository userBootcampRepository) {
        this.patikaService = patikaService;
        this.coderspaceService = coderspaceService;
        this.techcareerService = techcareerService;
        this.userBootcampRepository = userBootcampRepository;
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
//		System.out.println("aaaaaaaaaaaaaaaaaa");
//		userBootcampRepository.findByUserId("2463B147D92D491FA941BA3ABEC60D7a").stream()
//				.map(UserBootcamp::getBaseBootcamp)
//				.filter(baseBootcamp -> baseBootcamp instanceof Patika)
//				.map(baseBootcamp -> (Patika) baseBootcamp)
//				.toList().forEach(System.out::println);
//		System.out.println("aaaaaaaaaaaaaaaaaa");
//		userBootcampRepository.findPatikasByUserId("2463B147D92D491FA941BA3ABEC60D7a").forEach(System.out::println);
//		System.out.println("aaaaaaaaaaaaaaaaaa");
//		userBootcampRepository.findAll().forEach(System.out::println);

	}
}
