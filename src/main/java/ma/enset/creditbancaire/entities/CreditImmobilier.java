package ma.enset.creditbancaire.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@DiscriminatorValue("IMMOBILIER")
@Data @NoArgsConstructor @AllArgsConstructor
public class CreditImmobilier extends Credit {
    private String bienImmobilier;
    private String adresse;
}