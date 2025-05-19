package ma.enset.creditbancaire.services.impl;

import lombok.AllArgsConstructor;
import ma.enset.creditbancaire.entities.Credit;
import ma.enset.creditbancaire.entities.Remboursement;
import ma.enset.creditbancaire.repositories.CreditRepository;
import ma.enset.creditbancaire.repositories.RemboursementRepository;
import ma.enset.creditbancaire.services.RemboursementService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class RemboursementServiceImpl implements RemboursementService {
    private final RemboursementRepository remboursementRepository;
    private final CreditRepository creditRepository;

    @Override
    public List<Remboursement> listeRemboursements() {
        return remboursementRepository.findAll();
    }

    @Override
    public Remboursement getRemboursement(Long id) {
        return remboursementRepository.findById(id).orElseThrow(() -> 
            new RuntimeException("Remboursement not found with id: " + id));
    }

    @Override
    public List<Remboursement> getRemboursementsByCredit(Long creditId) {
        Credit credit = creditRepository.findById(creditId).orElseThrow(() ->
            new RuntimeException("Credit not found with id: " + creditId));
        return remboursementRepository.findByCredit(credit);
    }

    @Override
    public Remboursement saveRemboursement(Remboursement remboursement) {
        return remboursementRepository.save(remboursement);
    }

    @Override
    public void deleteRemboursement(Long id) {
        remboursementRepository.deleteById(id);
    }
}
