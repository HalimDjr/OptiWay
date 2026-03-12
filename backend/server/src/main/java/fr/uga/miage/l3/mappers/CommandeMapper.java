package fr.uga.miage.l3.mappers;

import fr.uga.miage.l3.models.CommandeEntity;
import fr.uga.miage.l3.responses.CommandResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface CommandeMapper {

    Set<CommandResponseDTO> toResponse(Set<CommandeEntity> commandes);
}
