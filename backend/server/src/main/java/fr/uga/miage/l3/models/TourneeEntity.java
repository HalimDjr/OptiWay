package fr.uga.miage.l3.models;

import fr.uga.miage.l3.enums.StatutTournee;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TourneeEntity {
    @Id
    private int idTournee;
    private double tempsTotal;
    @Enumerated(EnumType.STRING)
    private StatutTournee statut;
    private Timestamp heureDepart;
    private double distanceTotale;

    //OK TOURNEE COMMANDESs
    @ManyToMany
    @JoinTable(
            name = "tournee_commande",
            joinColumns = @JoinColumn(name = "tournee_id"),
            inverseJoinColumns = @JoinColumn(name = "commande_id")
    )
    private Set<CommandeEntity> commandes;

    @ManyToOne  //au lieu de @OneToOne
    private EquipeEntity equipe;
}
