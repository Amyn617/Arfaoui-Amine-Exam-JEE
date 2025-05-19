package ma.enset.creditbancaire.mappers;

import ma.enset.creditbancaire.dtos.ClientDTO;
import ma.enset.creditbancaire.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientDTO clientToClientDTO(Client client);
    
    List<ClientDTO> clientsToClientDTOs(List<Client> clients);
    
    @Mapping(target = "credits", ignore = true)
    Client clientDTOToClient(ClientDTO clientDTO);
}
