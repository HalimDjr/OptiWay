package fr.uga.miage.l3.component;

import fr.uga.miage.l3.models.SolutionEntity;
import fr.uga.miage.l3.repository.SolutionRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SolutionComponent {
    private final SolutionRepository solutionRepository;
    private final EntityManager entityManager;

    public SolutionEntity saveSolution(SolutionEntity solution) {
        return solutionRepository.save(solution);
    }

    public List<SolutionEntity> getAllSolutions() {
        return solutionRepository.findAll();
    }

    public SolutionEntity activerSolution(int id) {
        solutionRepository.desactiverToutesSolutions();
        SolutionEntity solution = solutionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solution " + id + " non trouvée"));
        solution.setActivee(true);
        return solutionRepository.save(solution);
    }

    @Transactional(value = Transactional.TxType.REQUIRES_NEW)
    public void deleteSolutionByAlgoAndToday(String nomAlgorithme) {
        // Construire la date du jour à minuit
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Date today = cal.getTime();

        Optional<SolutionEntity> existing =
                solutionRepository.findByNomAlgorithmeAndDate(nomAlgorithme, today);

        if (existing.isPresent()) {
            SolutionEntity solution = existing.get();

            // Vider les tournées → orphanRemoval supprime les tournées
            // et les lignes dans tournee_commande
            solution.getTournees().clear();
            solutionRepository.save(solution);
            entityManager.flush();

            // Supprimer la solution via JPA
            solutionRepository.delete(solution);
            entityManager.flush();

            System.out.println("Ancienne solution supprimée pour algo: " + nomAlgorithme);
        } else {
            System.out.println("Pas de solution existante aujourd'hui pour " + nomAlgorithme);
        }
    }
}