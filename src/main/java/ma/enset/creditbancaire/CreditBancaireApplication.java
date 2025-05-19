package ma.enset.creditbancaire;

import ma.enset.creditbancaire.dtos.ClientDTO;
import ma.enset.creditbancaire.services.ClientService;
import ma.enset.creditbancaire.services.CreditService;
import ma.enset.creditbancaire.services.RemboursementService;
import ma.enset.creditbancaire.entities.Client;
import ma.enset.creditbancaire.entities.Credit;
import ma.enset.creditbancaire.entities.CreditImmobilier;
import ma.enset.creditbancaire.entities.CreditPersonnel;
import ma.enset.creditbancaire.entities.Remboursement;
import ma.enset.creditbancaire.enums.StatutCredit;
import ma.enset.creditbancaire.enums.TypeBien;
import ma.enset.creditbancaire.enums.TypeRemboursement;
import ma.enset.creditbancaire.repositories.ClientRepository; // Added for fetching Client entity

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class CreditBancaireApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditBancaireApplication.class, args);
	}
	
	@Bean
	CommandLineRunner commandLineRunner(ClientService clientService, CreditService creditService, RemboursementService remboursementService, ClientRepository clientRepository) {
		return args -> {
			// Initialize sample clients
			ClientDTO client1DTO = clientService.saveClient(ClientDTO.builder()
					.nom("Amine")
					.prenom("Arfaoui")
					.email("amine.arfaoui@gmail.com")
					.telephone("0661223344")
					.adresse("123 Rue Hassan II, Casablanca")
					.dateNaissance("1985-05-12")
					.cin("BK123456")
					.build());
					
			ClientDTO client2DTO = clientService.saveClient(ClientDTO.builder()
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

			// Fetch Client entities to associate with Credits
			Client client1 = clientRepository.findById(client1DTO.getId()).orElse(null);
			Client client2 = clientRepository.findById(client2DTO.getId()).orElse(null);

			if (client1 != null) {
				// Sample Credit Personnel for Client 1
				CreditPersonnel creditPerso1 = new CreditPersonnel();
				creditPerso1.setClient(client1);
				creditPerso1.setDateDemande(LocalDate.now().minusMonths(2));
				creditPerso1.setMontant(15000.00);
				creditPerso1.setDureeRemboursement(24); // 24 months
				creditPerso1.setTauxInteret(0.05); // 5%
				creditPerso1.setStatut(StatutCredit.EN_COURS);
				Credit savedCreditPerso1 = creditService.saveCreditPersonnel(creditPerso1);
				System.out.println("Saved Credit Personnel ID: " + savedCreditPerso1.getId() + " for client " + client1.getNom());

				// Sample Remboursements for CreditPersonnel 1
				Remboursement remb1Perso1 = new Remboursement();
				remb1Perso1.setCredit(savedCreditPerso1);
				remb1Perso1.setDateEcheance(LocalDate.now().minusMonths(1).plusDays(15));
				remb1Perso1.setMontant(650.00);
				remb1Perso1.setPaye(false);
				remb1Perso1.setType(TypeRemboursement.MENSUALITE);
				remboursementService.saveRemboursement(remb1Perso1);

				Remboursement remb2Perso1 = new Remboursement();
				remb2Perso1.setCredit(savedCreditPerso1);
				remb2Perso1.setDateEcheance(LocalDate.now().plusDays(15));
				remb2Perso1.setMontant(650.00);
				remb2Perso1.setPaye(false);
				remb2Perso1.setType(TypeRemboursement.MENSUALITE);
				remboursementService.saveRemboursement(remb2Perso1);
				System.out.println("Added 2 remboursements for Credit ID: " + savedCreditPerso1.getId());


				// Accept the credit
				creditService.accepterCredit(savedCreditPerso1.getId());
				System.out.println("Credit ID: " + savedCreditPerso1.getId() + " accepted.");
			}

			if (client2 != null) {
				// Sample Credit Immobilier for Client 2
				CreditImmobilier creditImmo1 = new CreditImmobilier();
				creditImmo1.setClient(client2);
				creditImmo1.setDateDemande(LocalDate.now().minusDays(45));
				creditImmo1.setMontant(250000.00);
				creditImmo1.setDureeRemboursement(180); // 15 years
				creditImmo1.setTauxInteret(0.035); // 3.5%
				creditImmo1.setStatut(StatutCredit.EN_COURS);
				creditImmo1.setTypeBien(TypeBien.APPARTEMENT);
				creditImmo1.setAdresse("15 Rue de la Paix, Agadir");
				Credit savedCreditImmo1 = creditService.saveCreditImmobilier(creditImmo1);
				System.out.println("Saved Credit Immobilier ID: " + savedCreditImmo1.getId() + " for client " + client2.getNom());

				// Sample Remboursements for CreditImmobilier 1
				Remboursement remb1Immo1 = new Remboursement();
				remb1Immo1.setCredit(savedCreditImmo1);
				remb1Immo1.setDateEcheance(LocalDate.now().minusDays(10));
				remb1Immo1.setMontant(1700.00);
				remb1Immo1.setPaye(false);
				remb1Immo1.setType(TypeRemboursement.MENSUALITE);
				remboursementService.saveRemboursement(remb1Immo1);

				Remboursement remb2Immo1 = new Remboursement();
				remb2Immo1.setCredit(savedCreditImmo1);
				remb2Immo1.setDateEcheance(LocalDate.now().plusMonths(1).minusDays(10));
				remb2Immo1.setMontant(1700.00);
				remb2Immo1.setPaye(false);
				remb2Immo1.setType(TypeRemboursement.MENSUALITE);
				remboursementService.saveRemboursement(remb2Immo1);
				System.out.println("Added 2 remboursements for Credit ID: " + savedCreditImmo1.getId());

				// Reject the credit
				creditService.rejeterCredit(savedCreditImmo1.getId());
				System.out.println("Credit ID: " + savedCreditImmo1.getId() + " rejected.");
			}

			System.out.println("==== All Credits ====");
			creditService.listeCredits().forEach(c -> {
				System.out.println("Credit ID: " + c.getId() + ", Montant: " + c.getMontant() + ", Statut: " + c.getStatut() + ", Client: " + c.getClient().getNom());
				if (c.getRemboursements() != null && !c.getRemboursements().isEmpty()) {
					System.out.println("  Remboursements:");
					c.getRemboursements().forEach(r -> System.out.println("    - Echeance: " + r.getDateEcheance() + ", Montant: " + r.getMontant() + ", Payé: " + r.isPaye()));
				} else {
					System.out.println("  No remboursements for this credit yet.");
				}
			});

			System.out.println("==== All Remboursements ====");
			remboursementService.listeRemboursements().forEach(r -> {
				System.out.println("Remboursement ID: " + r.getId() + ", Credit ID: " + r.getCredit().getId() + ", Montant: " + r.getMontant() + ", Echeance: " + r.getDateEcheance());
			});

		};
	}
}
