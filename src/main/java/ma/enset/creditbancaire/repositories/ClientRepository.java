package ma.enset.creditbancaire.repositories;

import ma.enset.creditbancaire.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByNomContaining(String nom);
    Optional<Client> findByEmail(String email);
}
