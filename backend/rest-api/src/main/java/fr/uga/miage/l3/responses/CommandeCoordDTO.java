package fr.uga.miage.l3.responses;

import lombok.Data;

@Data
public class CommandeCoordDTO {
    private String numeroCommande;
    private double latitude;
    private double longtitude;
}