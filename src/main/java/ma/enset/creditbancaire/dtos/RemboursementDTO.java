package ma.enset.creditbancaire.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.creditbancaire.enums.TypeRemboursement;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemboursementDTO {
    private Long id;
    private LocalDate dateEcheance;
    private LocalDate datePaiement;
    private double montant;
    private boolean paye;
    private TypeRemboursement type;
    private Long creditId;
}
