package fr.uga.miage.l3.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.util.Set;

/// ///
@Entity
@Getter
@Setter
public class EquipeEntity {
    @Id
    private int numeroEquipe;
    private double nbHeuresMax;

    //OK EQUIPE TOURNEE
    @OneToOne(mappedBy = "equipe")
    private TourneeEntity tournee;

    //OK EQUIPE CONDUCTEURr
    @OneToOne
    private LivreurEntity conducteur ;

    //OK EQUIPE
    @OneToMany(mappedBy = "equipe2")
    private Set<LivreurEntity> manutentionnaires;


    @OneToOne
    private VehiculeEntity vehicule;
}

