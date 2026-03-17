package fr.uga.miage.l3.responses;

import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class SolutionResponseDTO {
    private int id;
    private String nomAlgorithme;
    private Date date;
    private boolean activee;
    private int nbCommandesLivrees;
    private double tempsTotal;
    private double distanceTotale;
    private double coutTotal;
    private int nbEquipesUtilisees;
    private Set<TourneeResponseDTO> tournees;
}