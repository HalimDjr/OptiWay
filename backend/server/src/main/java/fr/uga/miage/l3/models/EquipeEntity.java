package fr.uga.miage.l3.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Time;

@Entity
public class EquipeEntity {
    @Id
    private int numeroEquipe;
    private Time nombreHeuresMax;
}
