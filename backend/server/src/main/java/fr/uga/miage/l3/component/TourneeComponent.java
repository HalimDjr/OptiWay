package fr.uga.miage.l3.component;

import fr.uga.miage.l3.models.TourneeEntity;
import fr.uga.miage.l3.repository.TourneeRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TourneeComponent {
    private final TourneeRepository tourneeRepository;
    private final EntityManager entityManager;

    @Transactional
    public TourneeEntity createTournee(TourneeEntity tourneeEntity) {
        TourneeEntity saved = tourneeRepository.save(tourneeEntity);
        entityManager.flush();
        return saved;
    }

    public List<TourneeEntity> getTourneesByDate(LocalDate date) {
        Date sqlDate = Date.valueOf(date);
        return tourneeRepository.findByDateTournee(sqlDate);
    }

    public Optional<TourneeEntity> getTourneeById(int id) {
        return tourneeRepository.findById(id);
    }
    public void deleteTournee(int id) {
        tourneeRepository.deleteById(id);
    }
}