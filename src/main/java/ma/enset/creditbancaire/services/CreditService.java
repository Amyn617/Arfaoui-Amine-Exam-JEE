package ma.enset.creditbancaire.services;

import ma.enset.creditbancaire.entities.Credit;
import ma.enset.creditbancaire.entities.CreditImmobilier;
import ma.enset.creditbancaire.entities.CreditPersonnel;
import ma.enset.creditbancaire.entities.CreditProfessionnel;
import ma.enset.creditbancaire.enums.StatutCredit;

import java.util.List;

public interface CreditService {
    List<Credit> listeCredits();
    Credit getCredit(Long id);
    Credit saveCredit(Credit credit);
    void deleteCredit(Long id);
    List<Credit> getCreditsByClient(Long clientId);
    List<Credit> getCreditsByStatut(StatutCredit statut);
    CreditPersonnel saveCreditPersonnel(CreditPersonnel credit);
    CreditImmobilier saveCreditImmobilier(CreditImmobilier credit);
    CreditProfessionnel saveCreditProfessionnel(CreditProfessionnel credit);
    Credit accepterCredit(Long id);
    Credit rejeterCredit(Long id);
}
