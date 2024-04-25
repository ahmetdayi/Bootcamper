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

    public static void main(String[] args) {
		SpringApplication.run(BootcamperApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


	}
}
