package ma.enset.creditbancaire.entities;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PERSONNEL")
public class CreditPersonnel extends Credit {
    private String motif;
}