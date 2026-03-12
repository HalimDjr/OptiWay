package fr.uga.miage.l3.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
    @OneToOne(mappedBy ="equipe")
    private LivreurEntity conducteur ;

    //OK EQUIPE
    @OneToMany
    private Set<LivreurEntity> manutentionnaires;

    //OK VEHICULE TOURNEE
    @OneToOne(mappedBy = "equipe")
    private VehiculeEntity vehicule;
}

