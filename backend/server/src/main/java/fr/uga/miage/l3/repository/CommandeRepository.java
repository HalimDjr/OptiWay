package fr.uga.miage.l3.repository;

import fr.uga.miage.l3.enums.StatutCommande;
import fr.uga.miage.l3.models.CommandeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CommandeRepository extends JpaRepository<CommandeEntity,String>{
    Set<CommandeEntity> findByStatutIn(Set<StatutCommande> statuts);

    // Avec JPQL pour deux statuts précis
    @Query("SELECT c FROM CommandeEntity c WHERE c.statut = 'NON_PLANIFIEE' OR c.statut = 'ANNULEE'")
    Set<CommandeEntity> getNonPlanifieesOuAnnulees();
}
