package ma.enset.creditbancaire.mappers;

import ma.enset.creditbancaire.dtos.RemboursementDTO;
import ma.enset.creditbancaire.entities.Credit;
import ma.enset.creditbancaire.entities.Remboursement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RemboursementMapper {
    
    @Mapping(source = "credit.id", target = "creditId")
    RemboursementDTO remboursementToRemboursementDTO(Remboursement remboursement);
    
    List<RemboursementDTO> remboursementsToRemboursementDTOs(List<Remboursement> remboursements);
    
    @Mapping(source = "creditId", target = "credit", qualifiedByName = "idToCredit")
    Remboursement remboursementDTOToRemboursement(RemboursementDTO remboursementDTO);
    
    @Named("idToCredit")
    default Credit idToCredit(Long id) {
        if (id == null) {
            return null;
        }
        Credit credit = new Credit() {
        };
        credit.setId(id);
        return credit;
    }
}
