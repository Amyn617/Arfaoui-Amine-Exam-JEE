package ma.enset.creditbancaire.services;

import lombok.AllArgsConstructor;
import ma.enset.creditbancaire.dtos.ClientDTO;
import ma.enset.creditbancaire.entities.Client;
import ma.enset.creditbancaire.mappers.ClientMapper;
import ma.enset.creditbancaire.repositories.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

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
    public ClientDTO saveClient(ClientDTO clientDTO) {
        Client client = clientMapper.clientDTOToClient(clientDTO);
        Client savedClient = clientRepository.save(client);
        return clientMapper.clientToClientDTO(savedClient);
    }

    @Override
    public ClientDTO getClientById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Client not found with id: " + id));
        return clientMapper.clientToClientDTO(client);
    }

    @Override
    public List<ClientDTO> getAllClients() {
        List<Client> clients = clientRepository.findAll();
        return clientMapper.clientsToClientDTOs(clients);
    }

    @Override
    public ClientDTO updateClient(ClientDTO clientDTO) {
        // Check if client exists
        Client existingClient = clientRepository.findById(clientDTO.getId())
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + clientDTO.getId()));
                
        // Map DTO to entity
        Client client = clientMapper.clientDTOToClient(clientDTO);
        Client updatedClient = clientRepository.save(client);
        return clientMapper.clientToClientDTO(updatedClient);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public List<Client> chercherClients(String keyword) {
        // This would typically use a repository method that searches by various fields
        // For now, we'll just filter the results in memory
        return clientRepository.findAll().stream()
                .filter(client -> 
                    client.getNom().toLowerCase().contains(keyword.toLowerCase()) ||
                    client.getPrenom().toLowerCase().contains(keyword.toLowerCase()) ||
                    client.getEmail().toLowerCase().contains(keyword.toLowerCase())
                )
                .collect(Collectors.toList());
    }
}
