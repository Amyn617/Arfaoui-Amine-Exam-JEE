package ma.enset.creditbancaire.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String telephone;
    private String adresse;
    private String dateNaissance;
    private String cin;
    private String profession;
    private String entreprise;
    private String typeClient;
    private Double revenuMensuel;
    private String deviseRevenu;
}
