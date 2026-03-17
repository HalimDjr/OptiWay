package fr.uga.miage.l3.mappers;

import fr.uga.miage.l3.models.CommandeEntity;
import fr.uga.miage.l3.models.TourneeEntity;
import fr.uga.miage.l3.request.TourneeRequest;
import fr.uga.miage.l3.responses.CommandeCoordDTO;
import fr.uga.miage.l3.responses.TourneeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TourneeMapper {
    TourneeEntity toEntity(TourneeRequest tourneeRequest);

    @Mapping(target = "numeroEquipe", source = "equipe.numeroEquipe")
    @Mapping(target = "commandes", source = "commandes")
    TourneeResponseDTO toResponse(TourneeEntity tourneeEntity);

    @Mapping(target = "latitude", source = "adresse.latitude")
    @Mapping(target = "longtitude", source = "adresse.longitude")
    CommandeCoordDTO toCommandeCoord(CommandeEntity commandeEntity);
}
