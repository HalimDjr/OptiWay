package fr.uga.miage.l3.models;

import fr.uga.miage.l3.enums.StatutTournee;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
@Getter
@Setter
@Entity
public class TourneeEntity {
    @Id
    private int idTournee;
    private double tempsTotal;
    @Enumerated(EnumType.STRING)
    private StatutTournee statut;
    private Date dateTournee;
    private Timestamp heureDepart;
    private double distanceTotale;

    //OK TOURNEE COMMANDESs
    @OneToMany
    private Set<CommandeEntity> commandes;



    //OK EQUIPE TOURNEE
    @OneToOne
    private EquipeEntity equipe;



}
