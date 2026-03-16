package fr.uga.miage.l3.models;

import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
public class SolutionEntity {
    @Id
    private int id;
    private String nomAlgorithme;
    private Date date;
    private boolean activee;
    private int nbCommandesLivrees;
    private double tempsTotal;
    private double distanceTotale;
    private double coutTotal;
    private int nbEquipesUtilisees;

    @OneToMany
    @JoinColumn(name ="id_solution",  referencedColumnName = "id")
    private Set<TourneeEntity> tournees;





}
