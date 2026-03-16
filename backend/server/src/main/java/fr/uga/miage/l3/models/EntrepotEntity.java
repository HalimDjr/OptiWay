package fr.uga.miage.l3.models;

import jakarta.persistence.*;

@Entity
public class EntrepotEntity {
    @Id
    private int id;
    private String nom;

    @OneToOne
    @JoinColumn(name ="id_adresse", referencedColumnName = "id")
    private AdresseEntity adresse;
}


