package ma.enset.creditbancaire.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RemboursementDTO {
    private Long id;
    private LocalDate dateRemboursement;
    private double montant;
    private Long creditId;
}
