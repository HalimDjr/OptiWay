package fr.uga.miage.l3.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ProduitEntity {
    @Id
    private String reference;

    private String nom;

    private double poids;
}
