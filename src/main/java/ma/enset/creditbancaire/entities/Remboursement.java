package ma.enset.creditbancaire.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.enset.creditbancaire.enums.TypeRemboursement;

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
    
    @Enumerated(EnumType.STRING)
    private TypeRemboursement type;
    
    @ManyToOne
    private Credit credit;
}
