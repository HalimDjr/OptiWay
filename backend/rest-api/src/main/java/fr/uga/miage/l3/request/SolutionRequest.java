package fr.uga.miage.l3.request;

import lombok.Data;

import java.util.Set;

@Data
public class SolutionRequest {
    private String nomAlgorithme;      // "EQUITABLE", "SWEEP", "CLUSTER"
    private int nbEquipesUtilisees;
    private Set<Integer> tournees_ids; // ids des tournées déjà créées
}
