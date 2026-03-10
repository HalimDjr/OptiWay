package fr.uga.miage.l3.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.sql.Time;

@Entity
public class EquipeEntity {
    @Id
    private int numeroEquipe;
    private double nombreHeuresMax;

    @OneToOne
    private TourneeEntity tournee;
}

