package fr.uga.miage.l3.responses;

import lombok.Data;
import java.util.Set;

@Data
public class EquipeResponseDTO {
    private int numeroEquipe;
    private double nbHeuresMax;
    private LivreurResponseDTO conducteur;
    private Set<LivreurResponseDTO> manutentionnaires;
    private VehiculeResponseDTO vehicule;
}