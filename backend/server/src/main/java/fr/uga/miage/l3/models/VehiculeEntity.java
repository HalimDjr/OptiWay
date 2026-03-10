package fr.uga.miage.l3.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class VehiculeEntity {
    @Id
    private int matricule;
    private double poidsMax;
}
