package fr.uga.miage.l3.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class VehiculeEntity {
    @Id
    private int matricule;
    private double poidsMax;

    @OneToOne
    private TourneeEntity tournee;
}
