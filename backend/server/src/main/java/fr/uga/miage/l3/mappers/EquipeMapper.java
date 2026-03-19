package fr.uga.miage.l3.mappers;

import fr.uga.miage.l3.models.EquipeEntity;
import fr.uga.miage.l3.models.LivreurEntity;
import fr.uga.miage.l3.models.VehiculeEntity;
import fr.uga.miage.l3.responses.EquipeResponseDTO;
import fr.uga.miage.l3.responses.LivreurResponseDTO;
import fr.uga.miage.l3.responses.VehiculeResponseDTO;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EquipeMapper {
    EquipeResponseDTO toResponse(EquipeEntity equipeEntity);
    LivreurResponseDTO toLivreurResponse(LivreurEntity livreurEntity);
    VehiculeResponseDTO toVehiculeResponse(VehiculeEntity vehiculeEntity);
}