package fr.uga.miage.l3.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@Entity
public class VehiculeEntity {
    @Id
    private int matricule;
    private double capacitePoidsMax;
    private double capaciteVolumeMax;

    //OK VEHICULE TOURNEEe
    @OneToOne(mappedBy = "vehicule")
    private EquipeEntity equipe;
}
