package ma.enset.creditbancaire.repositories;

import ma.enset.creditbancaire.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByNomContainingIgnoreCase(String nom);
    List<Client> findByPrenomContainingIgnoreCase(String prenom);
    List<Client> findByEmailContainingIgnoreCase(String email);
    
    @Query("SELECT c FROM Client c WHERE " +
           "LOWER(c.nom) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(c.prenom) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(c.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(c.cin) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Client> searchClients(@Param("keyword") String keyword);
}
