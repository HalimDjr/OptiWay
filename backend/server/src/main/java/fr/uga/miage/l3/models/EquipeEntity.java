package fr.uga.miage.l3.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.sql.Time;
import java.util.Set;

@Entity
public class EquipeEntity {
    @Id
    private int numeroEquipe;
    private double nombreHeuresMax;

    @OneToOne
    private TourneeEntity tournee;

    @OneToOne
    private LivreurEntity livreur;

    @OneToMany
    private Set<LivreurEntity> livreurs;
}

