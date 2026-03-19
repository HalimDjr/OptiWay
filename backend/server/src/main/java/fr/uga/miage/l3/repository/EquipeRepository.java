package fr.uga.miage.l3.repository;

import fr.uga.miage.l3.models.EquipeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipeRepository extends JpaRepository<EquipeEntity, Integer> {

    EquipeEntity findTopByOrderByNbHeuresMaxDesc();

    @Query("SELECT DISTINCT e FROM EquipeEntity e LEFT JOIN FETCH e.conducteur LEFT JOIN FETCH e.manutentionnaires LEFT JOIN FETCH e.vehicule")
    List<EquipeEntity> findAllWithDetails();    
}