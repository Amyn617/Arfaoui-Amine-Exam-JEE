package ma.enset.creditbancaire.repositories;

import ma.enset.creditbancaire.entities.Credit;
import ma.enset.creditbancaire.entities.Remboursement;
import ma.enset.creditbancaire.enums.TypeRemboursement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemboursementRepository extends JpaRepository<Remboursement, Long> {
    List<Remboursement> findByCredit(Credit credit);
    List<Remboursement> findByCreditAndType(Credit credit, TypeRemboursement type);
}
