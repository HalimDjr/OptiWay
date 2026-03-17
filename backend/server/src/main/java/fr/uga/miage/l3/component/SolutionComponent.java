package fr.uga.miage.l3.component;

import fr.uga.miage.l3.models.SolutionEntity;
import fr.uga.miage.l3.repository.SolutionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class SolutionComponent {
    private final SolutionRepository solutionRepository;

    public SolutionEntity saveSolution(SolutionEntity solution) {
        try {
            return solutionRepository.save(solution);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la sauvegarde de la solution");
        }
    }

    public List<SolutionEntity> getAllSolutions() {
        try {
            return solutionRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la récupération des solutions");
        }
    }

    public SolutionEntity activerSolution(int id) {
        try {
            solutionRepository.desactiverToutesSolutions();
            SolutionEntity solution = solutionRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Solution " + id + " non trouvée"));
            solution.setActivee(true);
            return solutionRepository.save(solution);
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'activation de la solution " + id);
        }
    }
    public void deleteSolutionByAlgoAndToday(String nomAlgorithme) {
        try {
            solutionRepository.deleteByNomAlgorithmeAndToday(nomAlgorithme);
            System.out.println("Ancienne solution supprimée pour algo: " + nomAlgorithme);
        } catch (Exception e) {
            System.out.println("Pas de solution existante aujourd'hui pour " + nomAlgorithme);
        }
    }
}
