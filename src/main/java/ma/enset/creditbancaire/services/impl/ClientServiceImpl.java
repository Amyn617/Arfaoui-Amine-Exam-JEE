package ma.enset.creditbancaire.services.impl;

import lombok.AllArgsConstructor;
import ma.enset.creditbancaire.entities.Client;
import ma.enset.creditbancaire.repositories.ClientRepository;
import ma.enset.creditbancaire.services.ClientService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    public List<Client> listeClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClient(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> 
            new RuntimeException("Client not found with id: " + id));
    }

    @Override
    public Client saveClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> chercherClients(String keyword) {
        return clientRepository.findByNomContaining(keyword);
    }
}
