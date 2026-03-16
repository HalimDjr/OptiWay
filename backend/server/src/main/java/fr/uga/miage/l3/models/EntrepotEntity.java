package fr.uga.miage.l3.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EntrepotEntity {
    @Id
    private int id;
    private String nom;

    @OneToOne
    private AdresseEntity adresse;
}


