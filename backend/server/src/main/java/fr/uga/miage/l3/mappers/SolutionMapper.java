package fr.uga.miage.l3.mappers;

import fr.uga.miage.l3.models.SolutionEntity;
import fr.uga.miage.l3.responses.SolutionResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {TourneeMapper.class})
public interface SolutionMapper {
    SolutionResponseDTO toResponse(SolutionEntity solutionEntity);
}