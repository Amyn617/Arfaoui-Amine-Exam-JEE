package ma.enset.creditbancaire.services.impl;

import lombok.AllArgsConstructor;
import ma.enset.creditbancaire.entities.*;
import ma.enset.creditbancaire.enums.StatutCredit;
import ma.enset.creditbancaire.repositories.ClientRepository;
import ma.enset.creditbancaire.repositories.CreditImmobilierRepository;
import ma.enset.creditbancaire.repositories.CreditPersonnelRepository;
import ma.enset.creditbancaire.repositories.CreditProfessionnelRepository;
import ma.enset.creditbancaire.repositories.CreditRepository;
import ma.enset.creditbancaire.services.CreditService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class CreditServiceImpl implements CreditService {
    private final CreditRepository creditRepository;
    private final CreditPersonnelRepository creditPersonnelRepository;
    private final CreditImmobilierRepository creditImmobilierRepository;
    private final CreditProfessionnelRepository creditProfessionnelRepository;
    private final ClientRepository clientRepository;

    @Override
    public List<Credit> listeCredits() {
        return creditRepository.findAll();
    }

    @Override
    public Credit getCredit(Long id) {
        return creditRepository.findById(id).orElseThrow(() -> 
            new RuntimeException("Credit not found with id: " + id));
    }

    @Override
    public Credit saveCredit(Credit credit) {
        return creditRepository.save(credit);
    }

    @Override
    public void deleteCredit(Long id) {
        creditRepository.deleteById(id);
    }

    @Override
    public List<Credit> getCreditsByClient(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() ->
            new RuntimeException("Client not found with id: " + clientId));
        return creditRepository.findByClient(client);
    }

    @Override
    public List<Credit> getCreditsByStatut(StatutCredit statut) {
        return creditRepository.findByStatut(statut);
    }

    @Override
    public CreditPersonnel saveCreditPersonnel(CreditPersonnel credit) {
        return creditPersonnelRepository.save(credit);
    }

    @Override
    public CreditImmobilier saveCreditImmobilier(CreditImmobilier credit) {
        return creditImmobilierRepository.save(credit);
    }

    @Override
    public CreditProfessionnel saveCreditProfessionnel(CreditProfessionnel credit) {
        return creditProfessionnelRepository.save(credit);
    }

    @Override
    public Credit accepterCredit(Long id) {
        Credit credit = getCredit(id);
        credit.setStatut(StatutCredit.ACCEPTE);
        credit.setDateAcceptation(LocalDate.now());
        return creditRepository.save(credit);
    }

    @Override
    public Credit rejeterCredit(Long id) {
        Credit credit = getCredit(id);
        credit.setStatut(StatutCredit.REJETE);
        return creditRepository.save(credit);
    }
}
