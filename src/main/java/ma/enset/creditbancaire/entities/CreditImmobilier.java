package ma.enset.creditbancaire.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.enset.creditbancaire.enums.TypeBien;

@Entity
@DiscriminatorValue("IMMOBILIER")
@Data @NoArgsConstructor @AllArgsConstructor
public class CreditImmobilier extends Credit {
    @Enumerated(EnumType.STRING)
    private TypeBien typeBien;
    private String adresse;
}