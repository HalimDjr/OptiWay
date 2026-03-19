package fr.uga.miage.l3.repository;

import fr.uga.miage.l3.models.SolutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.Optional;

public interface SolutionRepository extends JpaRepository<SolutionEntity, Integer> {

    @Query("SELECT s FROM SolutionEntity s WHERE s.nomAlgorithme = :algo AND CAST(s.date AS date) = CURRENT_DATE")
    Optional<SolutionEntity> findByNomAlgorithmeAndToday(@Param("algo") String algo);

    Optional<SolutionEntity> findByActiveeTrue();

    // JPQL car pas possible par nom
    @Modifying
    @Query("UPDATE SolutionEntity s SET s.activee = false")
    void desactiverToutesSolutions();
}