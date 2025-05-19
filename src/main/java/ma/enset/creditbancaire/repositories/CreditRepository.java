package ma.enset.creditbancaire.repositories;

import ma.enset.creditbancaire.entities.Client;
import ma.enset.creditbancaire.entities.Credit;
import ma.enset.creditbancaire.enums.StatutCredit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    List<Credit> findByClient(Client client);
    List<Credit> findByStatut(StatutCredit statut);
    List<Credit> findByMontantGreaterThan(Double montant);
}