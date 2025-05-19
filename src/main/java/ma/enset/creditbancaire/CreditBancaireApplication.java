package ma.enset.creditbancaire;

import ma.enset.creditbancaire.dtos.ClientDTO;
import ma.enset.creditbancaire.services.ClientService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CreditBancaireApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditBancaireApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(ClientService clientService) {
		return args -> {
			// Initialize sample clients
			clientService.saveClient(ClientDTO.builder()
					.nom("Amine")
					.prenom("Arfaoui")
					.email("amine.arfaoui@gmail.com")
					.telephone("0661223344")
					.adresse("123 Rue Hassan II, Casablanca")
					.dateNaissance("1985-05-12")
					.cin("BK123456")
					.build());
					
			clientService.saveClient(ClientDTO.builder()
					.nom("Benani")
					.prenom("Salma")
					.email("benani.salma@gmail.com")
					.telephone("0677889900")
					.adresse("45 Avenue Mohammed V, Rabat")
					.dateNaissance("1990-10-25")
					.cin("AB789012")
					.build());
					
			clientService.saveClient(ClientDTO.builder()
					.nom("Mansouri")
					.prenom("Younes")
					.email("mansouri.younes@gmail.com")
					.telephone("0633445566")
					.adresse("78 Boulevard Zerktouni, Marrakech")
					.dateNaissance("1982-08-17")
					.cin("CD345678")
					.build());


			List<ClientDTO> clients = clientService.getAllClients();
			System.out.println("==== Clients List ====");
			clients.forEach(client -> {
				System.out.println(client.getNom() + " " + client.getPrenom() + " - " + client.getEmail());
			});
		};
	}
}
