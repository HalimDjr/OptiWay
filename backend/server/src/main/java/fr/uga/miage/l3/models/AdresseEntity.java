package fr.uga.miage.l3.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AdresseEntity  {
    @Id
    private Long id;
    private int numeroRue;
    private int codePostal;
    private String paye;
    private double latitude;
    private double longitude;
}
