package fr.uga.miage.l3.repository;

import fr.uga.miage.l3.models.SolutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.Optional;

public interface SolutionRepository extends JpaRepository<SolutionEntity, Integer> {

    // Interprétation par nom
    Optional<SolutionEntity> findByNomAlgorithmeAndDate(String nomAlgorithme, Date date);

    Optional<SolutionEntity> findByActiveeTrue();

    // JPQL car pas possible par nom
    @Modifying
    @Query("UPDATE SolutionEntity s SET s.activee = false")
    void desactiverToutesSolutions();
}