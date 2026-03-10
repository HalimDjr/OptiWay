package fr.uga.miage.l3.models;

import fr.uga.miage.l3.enums.StatutCommande;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.util.Date;
import java.util.Set;

@Entity
public class CommandeEntity {
    @Id
    private String numeroCommande;
    private Date dernierDelai;
    private StatutCommande statutCommande;
    private double poids;

    @OneToOne(mappedBy = "commande")
    private TourneeEntity tournee;

    @OneToMany
    private Set<ProduitEntity> produits;


}
