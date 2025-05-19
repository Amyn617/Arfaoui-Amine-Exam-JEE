package ma.enset.creditbancaire.repositories;

import ma.enset.creditbancaire.entities.CreditProfessionnel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditProfessionnelRepository extends JpaRepository<CreditProfessionnel, Long> {
    List<CreditProfessionnel> findByRaisonSocialeEntreprise(String raisonSociale);
}