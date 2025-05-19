package ma.enset.creditbancaire.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.enset.creditbancaire.enums.StatutCredit;

import java.time.LocalDate;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "type_credit")
@Data @NoArgsConstructor @AllArgsConstructor
public abstract class Credit {
    @Id @GeneratedValue
    private Long id;
    private LocalDate dateDemande;
    @Enumerated(EnumType.STRING)
    private StatutCredit statut;
    private LocalDate dateAcceptation;
    private double montant;
    private int dureeRemboursement;
    private double tauxInteret;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "credit")
    private List<Remboursement> remboursements;
}
