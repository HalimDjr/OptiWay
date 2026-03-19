package fr.uga.miage.l3.responses;

import lombok.Data;

@Data
public class LivreurResponseDTO {
    private int idLivreur;
    private String nom;
    private String prenom;
    private String telephone;
    private boolean permis;
}