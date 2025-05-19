package ma.enset.creditbancaire.services;

import ma.enset.creditbancaire.entities.Remboursement;

import java.util.List;

public interface RemboursementService {
    List<Remboursement> listeRemboursements();
    Remboursement getRemboursement(Long id);
    List<Remboursement> getRemboursementsByCredit(Long creditId);
    Remboursement saveRemboursement(Remboursement remboursement);
    void deleteRemboursement(Long id);
}
