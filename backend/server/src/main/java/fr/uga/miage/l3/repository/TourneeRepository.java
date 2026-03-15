package fr.uga.miage.l3.repository;

import java.sql.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.uga.miage.l3.models.TourneeEntity;

public interface TourneeRepository extends JpaRepository<TourneeEntity,Integer> {
    //Extrait uniquement la partie DATE du timestamp (ex: 2026-03-18 01:00:00 → 2026-03-18)
    @Query(value = "SELECT * FROM tournee_entity WHERE DATE(date_tournee) = :date",
            nativeQuery = true)
    List<TourneeEntity> findByDateTournee(@Param("date") Date date);
}