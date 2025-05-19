package ma.enset.creditbancaire.entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PROFESSIONNEL")
public class CreditProfessionnel extends Credit {
    private String motif;
    private String raisonSociale;
}