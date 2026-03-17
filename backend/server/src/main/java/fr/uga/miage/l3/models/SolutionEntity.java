package fr.uga.miage.l3.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
public class SolutionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nomAlgorithme;
    private Date date;
    private boolean activee;
    private int nbCommandesLivrees;
    private double tempsTotal;
    private double distanceTotale;
    private double coutTotal;
    private int nbEquipesUtilisees;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, orphanRemoval = true)
    @JoinColumn(name ="id_solution",  referencedColumnName = "id")
    private Set<TourneeEntity> tournees;





}
