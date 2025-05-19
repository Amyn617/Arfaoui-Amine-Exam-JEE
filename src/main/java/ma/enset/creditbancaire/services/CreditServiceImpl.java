package ma.enset.creditbancaire.services;

import lombok.AllArgsConstructor;
import ma.enset.creditbancaire.entities.*;
import ma.enset.creditbancaire.enums.StatutCredit;
import ma.enset.creditbancaire.exceptions.CreditException;
import ma.enset.creditbancaire.exceptions.ResourceNotFoundException;
import ma.enset.creditbancaire.repositories.ClientRepository;
import ma.enset.creditbancaire.repositories.CreditImmobilierRepository;
import ma.enset.creditbancaire.repositories.CreditPersonnelRepository;
import ma.enset.creditbancaire.repositories.CreditProfessionnelRepository;
import ma.enset.creditbancaire.repositories.CreditRepository;
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
            new ResourceNotFoundException("Credit", id));
    }

    @Override
    public Credit saveCredit(Credit credit) {
        if (credit.getMontant() <= 0) {
            throw CreditException.invalidAmount(credit.getMontant());
        }
        return creditRepository.save(credit);
    }

    @Override
    public void deleteCredit(Long id) {
        creditRepository.deleteById(id);
    }

    @Override
    public List<Credit> getCreditsByClient(Long clientId) {
        Client client = clientRepository.findById(clientId).orElseThrow(() ->
            new ResourceNotFoundException("Client", clientId));
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
        if (credit.getStatut() != StatutCredit.EN_ATTENTE) {
            throw CreditException.creditAlreadyProcessed(id);
        }
        credit.setStatut(StatutCredit.ACCEPTE);
        credit.setDateAcceptation(LocalDate.now());
        return creditRepository.save(credit);
    }

    @Override
    public Credit rejeterCredit(Long id) {
        Credit credit = getCredit(id);
        if (credit.getStatut() != StatutCredit.EN_ATTENTE) {
            throw CreditException.creditAlreadyProcessed(id);
        }
        credit.setStatut(StatutCredit.REJETE);
        return creditRepository.save(credit);
    }
}
