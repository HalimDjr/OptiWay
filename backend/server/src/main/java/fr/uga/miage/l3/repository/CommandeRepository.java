package fr.uga.miage.l3.repository;

import fr.uga.miage.l3.enums.StatutCommande;
import fr.uga.miage.l3.models.CommandeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface CommandeRepository extends JpaRepository<CommandeEntity,String>{
    @Query("SELECT c FROM CommandeEntity c WHERE c.statut = 'NON_PLANIFIEE' OR c.statut = 'ANNULEE'")
    Set<CommandeEntity> getNonPlanifieesOuAnnulees();
   
    @Modifying 
    @Query("UPDATE CommandeEntity c SET c.statut = :statut WHERE c.numeroCommande IN :ids")
    void updateStatutByIds(@Param("ids") Set<String> ids, @Param("statut") StatutCommande statut);

}
