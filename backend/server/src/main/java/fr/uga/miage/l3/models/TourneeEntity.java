package fr.uga.miage.l3.models;

import fr.uga.miage.l3.enums.StatutTournee;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

@Entity
public class TourneeEntity {
    @Id
    private int idTournee;
    private double tempsTotal;
    private StatutTournee statut;
    private Date dateTournee;
    private Timestamp heureDepart;
    private double distanceTotale;

    //OK TOURNEE COMMANDESs
    @OneToMany
    private Set<CommandeEntity> commandes;

    //OK VEHICULE TOURNEE
    @OneToOne(mappedBy = "vehicule")
    private VehiculeEntity vehicule;

    //OK EQUIPE TOURNEE
    @OneToOne
    private EquipeEntity equipe;



}
