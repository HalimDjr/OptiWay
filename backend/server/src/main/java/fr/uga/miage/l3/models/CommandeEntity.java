package fr.uga.miage.l3.models;

import fr.uga.miage.l3.enums.StatutCommande;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
@Getter
@Setter
@Entity
public class CommandeEntity {
    //
    @Id
    private String numeroCommande;
    private Date dateLimite;
    @Enumerated(EnumType.STRING)
    private StatutCommande statut;
    private double poids;
    private double volume;

//ENLEVER CELUIS LA
    //OK AVEC ADRESSE

    @ManyToOne
    @JoinColumn(name = "adresse_id", referencedColumnName = "id")
    private AdresseEntity adresse;


    //OK COMMANDE PRODUIT
    @OneToMany //MAPPEDBY
    @JoinColumn(name="commande_id",referencedColumnName = "numeroCommande")
    private Set<ProduitEntity> produits;


    //OK COMMANDE TOURNEE
    @ManyToOne
    private TourneeEntity tournee;



}
