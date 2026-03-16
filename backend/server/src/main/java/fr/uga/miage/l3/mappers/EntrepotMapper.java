package fr.uga.miage.l3.mappers;

import fr.uga.miage.l3.models.EntrepotEntity;
import fr.uga.miage.l3.responses.EntrepotResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EntrepotMapper {

    @Mapping(target="longtitude" , source = "adresse.longitude")
    @Mapping(target = "latitude", source="adresse.latitude")
    EntrepotResponseDTO toResponse(EntrepotEntity entrepotEntity);

}
