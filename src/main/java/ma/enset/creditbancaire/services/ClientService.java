package ma.enset.creditbancaire.services;

import ma.enset.creditbancaire.entities.Client;

import java.util.List;

public interface ClientService {
    List<Client> listeClients();
    Client getClient(Long id);
    Client saveClient(Client client);
    void deleteClient(Long id);
    List<Client> chercherClients(String keyword);
}
