package ma.enset.creditbancaire;

import ma.enset.creditbancaire.services.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CreditBancaireApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditBancaireApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ClientService clientService) {
		return args -> {
		};
	}
}
