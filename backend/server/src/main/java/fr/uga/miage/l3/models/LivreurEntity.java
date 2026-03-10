package fr.uga.miage.l3.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class LivreurEntity {
    @Id
    private int idLivreur;
    private String nom;
    private String prenom;
    private boolean isPermisDeConduire;
    private String numeroTelephone;
}
