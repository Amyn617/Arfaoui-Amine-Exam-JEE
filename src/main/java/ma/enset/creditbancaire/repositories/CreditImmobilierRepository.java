package ma.enset.creditbancaire.repositories;

import ma.enset.creditbancaire.entities.CreditImmobilier;
import ma.enset.creditbancaire.enums.TypeBien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CreditImmobilierRepository extends JpaRepository<CreditImmobilier, Long> {
    List<CreditImmobilier> findByTypeBien(TypeBien typeBien);
}
