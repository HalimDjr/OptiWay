package fr.uga.miage.l3.mappers;

import fr.uga.miage.l3.models.CommandeEntity;
import fr.uga.miage.l3.responses.CommandResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.text.SimpleDateFormat;

@Mapper(componentModel = "spring")
public interface CommandeMapper {


    CommandResponseDTO toResponse(CommandeEntity commande);


}