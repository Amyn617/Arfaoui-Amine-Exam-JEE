package ma.enset.creditbancaire.entities;

import jakarta.persistence.*;
import lombok.*;
import ma.enset.creditbancaire.enums.TypeBienImmobilier;

@Entity
@DiscriminatorValue("IMMOBILIER")
@Data @NoArgsConstructor @AllArgsConstructor
public class CreditImmobilier extends Credit {
    @Enumerated(EnumType.STRING)
    private TypeBienImmobilier typeBien;
    private String adresse;
}