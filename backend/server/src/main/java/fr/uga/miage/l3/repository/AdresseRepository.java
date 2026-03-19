package fr.uga.miage.l3.repository;

import fr.uga.miage.l3.models.AdresseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdresseRepository extends JpaRepository<AdresseEntity, Long> {
}