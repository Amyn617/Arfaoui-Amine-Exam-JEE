package ma.enset.creditbancaire.mappers;

import ma.enset.creditbancaire.dtos.CreditDTO;
import ma.enset.creditbancaire.dtos.CreditImmobilierDTO;
import ma.enset.creditbancaire.dtos.CreditPersonnelDTO;
import ma.enset.creditbancaire.dtos.CreditProfessionnelDTO;
import ma.enset.creditbancaire.entities.Client;
import ma.enset.creditbancaire.entities.Credit;
import ma.enset.creditbancaire.entities.CreditImmobilier;
import ma.enset.creditbancaire.entities.CreditPersonnel;
import ma.enset.creditbancaire.entities.CreditProfessionnel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CreditMapper {
    
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(target = "remboursements", ignore = true)
    CreditDTO creditToCreditDTO(Credit credit);
    
    List<CreditDTO> creditsToCreditDTOs(List<Credit> credits);
    
    @Mapping(source = "clientId", target = "client", qualifiedByName = "idToClient")
    @Mapping(target = "remboursements", ignore = true)
    Credit creditDTOToCredit(CreditDTO creditDTO);
    
    @Mapping(source = "client.id", target = "clientId")
    CreditPersonnelDTO creditPersonnelToCreditPersonnelDTO(CreditPersonnel creditPersonnel);
    
    @Mapping(source = "clientId", target = "client", qualifiedByName = "idToClient")
    @Mapping(target = "remboursements", ignore = true)
    CreditPersonnel creditPersonnelDTOToCreditPersonnel(CreditPersonnelDTO creditPersonnelDTO);
    
    @Mapping(source = "client.id", target = "clientId")
    CreditImmobilierDTO creditImmobilierToCreditImmobilierDTO(CreditImmobilier creditImmobilier);
    
    @Mapping(source = "clientId", target = "client", qualifiedByName = "idToClient")
    @Mapping(target = "remboursements", ignore = true)
    CreditImmobilier creditImmobilierDTOToCreditImmobilier(CreditImmobilierDTO creditImmobilierDTO);
    
    @Mapping(source = "client.id", target = "clientId")
    CreditProfessionnelDTO creditProfessionnelToCreditProfessionnelDTO(CreditProfessionnel creditProfessionnel);
    
    @Mapping(source = "clientId", target = "client", qualifiedByName = "idToClient")
    @Mapping(target = "remboursements", ignore = true)
    CreditProfessionnel creditProfessionnelDTOToCreditProfessionnel(CreditProfessionnelDTO creditProfessionnelDTO);
    
    @Named("idToClient")
    default Client idToClient(Long id) {
        if (id == null) {
            return null;
        }
        Client client = new Client();
        client.setId(id);
        return client;
    }
}
