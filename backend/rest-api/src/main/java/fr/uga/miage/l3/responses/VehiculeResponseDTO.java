package fr.uga.miage.l3.responses;

import lombok.Data;

@Data
public class VehiculeResponseDTO {
    private int matricule;
    private double capacitePoidsMax;
    private double capaciteVolumeMax;
}