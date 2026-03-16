package fr.uga.miage.l3.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;

@Entity
public class LivreurEntity {
    @Id
    private int idLivreur;
    private String nom;
    private String prenom;
    private boolean permis;
    private String telephone;

    //OK LIVREUR EQUIPEe
    @OneToOne(mappedBy ="conducteur")
    private EquipeEntity equipe;

    @ManyToOne
    @Size(min=1,max=2)
    private EquipeEntity equipe2;
}
