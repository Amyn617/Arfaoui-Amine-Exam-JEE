package ma.enset.creditbancaire.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Remboursement {
    @Id @GeneratedValue
    private Long id;
    private LocalDate dateEcheance;
    private LocalDate datePaiement;
    private double montant;
    private boolean paye;
    
    @ManyToOne
    private Credit credit;
}
