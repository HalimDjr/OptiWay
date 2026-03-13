package fr.uga.miage.l3.repository;

import fr.uga.miage.l3.enums.StatutCommande;
import fr.uga.miage.l3.models.CommandeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CommandeRepository extends JpaRepository<CommandeEntity,String>{
    Set<CommandeEntity> findCommandeEntitiesByStatutEquals (StatutCommande statusSouhaité);

    int countByStatut(StatutCommande statut);
}
