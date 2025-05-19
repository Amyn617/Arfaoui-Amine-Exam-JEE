package ma.enset.creditbancaire.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import ma.enset.creditbancaire.enums.TypeBien;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CreditImmobilierDTO extends CreditDTO {
    private TypeBien typeBien;
    private String adresse;
}
