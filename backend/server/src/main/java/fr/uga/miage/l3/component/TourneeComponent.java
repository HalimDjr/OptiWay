package fr.uga.miage.l3.component;


import fr.uga.miage.l3.models.TourneeEntity;

import fr.uga.miage.l3.repository.TourneeRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TourneeComponent {
    private final TourneeRepository tourneeRepository;
    private final EntityManager entityManager;
    @Transactional
    public TourneeEntity createTournee(TourneeEntity tourneeEntity) {
        TourneeEntity saved = tourneeRepository.save(tourneeEntity);
        entityManager.flush(); // ← forcer l'écriture immédiate en base
        return saved;
    }
    public List<TourneeEntity> getTourneesByDate(LocalDate date) {
        // Convertir LocalDate en Date pour la requête
        Date sqlDate = Date.valueOf(date);
        return tourneeRepository.findByDateTournee(sqlDate);
    }

    public Optional<TourneeEntity> getTourneeById(int id) {
        return tourneeRepository.findById(id);
    }
}
