package fr.uga.miage.l3.mappers;

import fr.uga.miage.l3.models.EquipeEntity;
import fr.uga.miage.l3.responses.EquipeResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipeMapper {
    EquipeResponseDTO toResponse(EquipeEntity equipeEntity);
}

