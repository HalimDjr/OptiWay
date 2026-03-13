package fr.uga.miage.l3.mappers;

import fr.uga.miage.l3.models.CommandeEntity;
import fr.uga.miage.l3.models.TourneeEntity;
import fr.uga.miage.l3.request.TourneeRequest;
import fr.uga.miage.l3.responses.CommandResponseDTO;
import fr.uga.miage.l3.responses.TourneeResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TourneeMapper {

    TourneeEntity toEntity(TourneeRequest tourneeRequest);
    CommandeEntity map(CommandResponseDTO value);
    TourneeResponseDTO toResponse(TourneeEntity tourneeEntity);
}
