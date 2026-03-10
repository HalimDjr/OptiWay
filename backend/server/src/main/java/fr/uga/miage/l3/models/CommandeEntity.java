package fr.uga.miage.l3.models;

import fr.uga.miage.l3.enums.StatutCommande;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class CommandeEntity {
    @Id
    private String numeroCommande;
    private Date dernierDelai;
    private StatutCommande statutCommande;
    private double poids;


}
