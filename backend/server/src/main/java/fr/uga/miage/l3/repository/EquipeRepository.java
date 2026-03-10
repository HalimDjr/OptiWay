package fr.uga.miage.l3.repository;

import fr.uga.miage.l3.models.EquipeEntity;
import fr.uga.miage.l3.models.ProduitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipeRepository extends JpaRepository<EquipeEntity, Integer> {

}
