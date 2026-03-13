package fr.uga.miage.l3.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@Entity
public class AdresseEntity  {
    //
    @Id
    private Long id;
    private int numeroRue;
    private String rue;
    private String ville;
    private int codePostal;
    private String pays;
    private double latitude;
    private double longitude;

    @OneToMany
    @JoinColumn(name = "adresse_id", referencedColumnName = "id")
    private Set<CommandeEntity> commandes;
}
