package fr.uga.miage.l3.models;

import fr.uga.miage.l3.enums.StatutTournee;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.sql.Time;
import java.util.Date;

@Entity
public class TourneeEntity {
    @Id
    private int idTournee;
    private Time temps;
    private StatutTournee statutTournee;
    private Date date;
    private Time heureDepart;

    @OneToOne(mappedBy = "vehicule")
    private VehiculeEntity vehicule;

    @OneToOne(mappedBy = "equipe")
    private EquipeEntity equipe;

}
