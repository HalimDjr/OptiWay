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
        // Plus besoin de calculer la date manuellement
        Optional<SolutionEntity> existing =
                solutionRepository.findByNomAlgorithmeAndToday(nomAlgorithme);

        System.out.println("Solution trouvée pour suppression: " + existing.isPresent());

        if (existing.isPresent()) {
            SolutionEntity solution = existing.get();
            solution.getTournees().clear();
            solutionRepository.save(solution);
            entityManager.flush();
            solutionRepository.delete(solution);
            entityManager.flush();
            System.out.println("Ancienne solution supprimée pour algo: " + nomAlgorithme);
        } else {
            System.out.println("Pas de solution existante aujourd'hui pour: " + nomAlgorithme);
        }
    }
}