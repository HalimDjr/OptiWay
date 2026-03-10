package fr.uga.miage.l3.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.uga.miage.l3.models.TourneeEntity;

public interface TourneeRepository extends JpaRepository<TourneeEntity,Integer> {
    
}
