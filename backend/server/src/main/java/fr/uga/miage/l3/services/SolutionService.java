package fr.uga.miage.l3.services;

import fr.uga.miage.l3.component.CommandComponent;
import fr.uga.miage.l3.component.SolutionComponent;
import fr.uga.miage.l3.component.TourneeComponent;
import fr.uga.miage.l3.enums.StatutCommande;
import fr.uga.miage.l3.mappers.SolutionMapper;
import fr.uga.miage.l3.models.CommandeEntity;
import fr.uga.miage.l3.models.SolutionEntity;
import fr.uga.miage.l3.models.TourneeEntity;
import fr.uga.miage.l3.repository.SolutionRepository;
import fr.uga.miage.l3.repository.CommandeRepository;
import fr.uga.miage.l3.request.SolutionRequest;
import fr.uga.miage.l3.responses.SolutionResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class SolutionService {
    private final SolutionComponent solutionComponent;
    private final TourneeComponent tourneeComponent;
    private final SolutionMapper solutionMapper;
    private final SolutionRepository solutionRepository;

    public SolutionResponseDTO saveSolution(SolutionRequest request) {
        // Supprimer l'ancienne solution du même algo aujourd'hui
        solutionComponent.deleteSolutionByAlgoAndToday(request.getNomAlgorithme());

        // Récupérer les tournées par leurs ids
        Set<TourneeEntity> tournees = request.getTournees_ids().stream()
                .map(id -> tourneeComponent.getTourneeById(id)
                        .orElseThrow(() -> new RuntimeException("Tournée " + id + " non trouvée")))
                .collect(Collectors.toSet());

        // Calculer les stats
        double tempsTotal = tournees.stream()
                .mapToDouble(TourneeEntity::getTempsTotal).sum();
        double distanceTotale = tournees.stream()
                .mapToDouble(TourneeEntity::getDistanceTotale).sum();
        int nbCommandes = tournees.stream()
                .mapToInt(t -> t.getCommandes().size()).sum();

        SolutionEntity solution = new SolutionEntity();
        solution.setNomAlgorithme(request.getNomAlgorithme());
        solution.setDate(new Date());
        solution.setActivee(false);
        solution.setNbCommandesLivrees(nbCommandes);
        solution.setTempsTotal(tempsTotal);
        solution.setDistanceTotale(distanceTotale);
        solution.setCoutTotal((distanceTotale * 8.0 / 100.0) * 1.85);
        solution.setNbEquipesUtilisees(request.getNbEquipesUtilisees());
        solution.setTournees(tournees);

        SolutionEntity saved = solutionComponent.saveSolution(solution);
        return solutionMapper.toResponse(saved);
    }

    public List<SolutionResponseDTO> getAllSolutions() {
        return solutionComponent.getAllSolutions().stream()
                .map(solutionMapper::toResponse)
                .collect(Collectors.toList());
    }

    public SolutionResponseDTO activerSolution(int id) {
        return solutionMapper.toResponse(
                solutionComponent.activerSolution(id)
        );
    }

    public SolutionResponseDTO getSolutionById(int id) {
        SolutionEntity solution = solutionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Solution " + id + " non trouvée"));
        return solutionMapper.toResponse(solution);
    }

public SolutionResponseDTO updateSolution(int id, SolutionRequest request) {
    System.out.println("updateSolution id=" + id + " tempsTotal=" + request.getTempsTotal() + " distanceTotale=" + request.getDistanceTotale() + " nbCommandes=" + request.getNbCommandesLivrees());
    
    SolutionEntity solution = solutionRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Solution " + id + " non trouvée"));

    solution.setTempsTotal(request.getTempsTotal());
    solution.setDistanceTotale(request.getDistanceTotale());
    solution.setCoutTotal((request.getDistanceTotale() * 8.0 / 100.0) * 1.85);
    solution.setNbCommandesLivrees(request.getNbCommandesLivrees());

    SolutionEntity saved = solutionRepository.saveAndFlush(solution);
    return solutionMapper.toResponse(saved);
}
private final CommandComponent commandComponent;
@Transactional
public void planifierCommandes(int solutionId) {
    SolutionEntity solution = solutionRepository.findById(solutionId)
            .orElseThrow(() -> new RuntimeException("Solution " + solutionId + " non trouvée"));

    Set<String> commandeIds = solution.getTournees().stream()
            .flatMap(t -> t.getCommandes().stream())
            .map(CommandeEntity::getNumeroCommande)
            .collect(Collectors.toSet());

    commandComponent.updateStatutCommandes(commandeIds, StatutCommande.PLANIFIEE);
}
}