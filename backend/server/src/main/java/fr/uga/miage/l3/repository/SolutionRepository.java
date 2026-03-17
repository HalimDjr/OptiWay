package fr.uga.miage.l3.repository;


import fr.uga.miage.l3.models.SolutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface SolutionRepository extends JpaRepository<SolutionEntity, Integer> {

    @Query("SELECT s FROM SolutionEntity s WHERE s.activee = true")
    Optional<SolutionEntity> findSolutionActivee();

    @Modifying
    @Query("UPDATE SolutionEntity s SET s.activee = false")
    void desactiverToutesSolutions();

    @Query("SELECT s FROM SolutionEntity s WHERE s.nomAlgorithme = :algo AND FUNCTION('DATE', s.date) = CURRENT_DATE")
    Optional<SolutionEntity> findByNomAlgorithmeAndToday(@Param("algo") String algo);

    @Modifying
    @Query("DELETE FROM SolutionEntity s WHERE s.nomAlgorithme = :algo AND FUNCTION('DATE', s.date) = CURRENT_DATE")
    void deleteByNomAlgorithmeAndToday(@Param("algo") String algo);
}
