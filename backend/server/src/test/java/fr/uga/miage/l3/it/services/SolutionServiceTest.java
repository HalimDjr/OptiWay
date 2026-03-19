package fr.uga.miage.l3.it.services;

import fr.uga.miage.l3.enums.StatutTournee;
import fr.uga.miage.l3.models.TourneeEntity;
import fr.uga.miage.l3.repository.SolutionRepository;
import fr.uga.miage.l3.repository.TourneeRepository;
import fr.uga.miage.l3.request.SolutionRequest;
import fr.uga.miage.l3.responses.SolutionResponseDTO;
import fr.uga.miage.l3.services.SolutionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase
@SpringBootTest
class SolutionServiceTest {

    @Autowired
    private SolutionService solutionService;

    @Autowired
    private SolutionRepository solutionRepository;

    @Autowired
    private TourneeRepository tourneeRepository;

    @BeforeEach
    void setUp() {
        solutionRepository.deleteAll(); // SolutionEntity a cascade REMOVE sur tournées
        tourneeRepository.deleteAll();
    }

    // -----------------------------------------------------------------------
    // saveSolution()
    // -----------------------------------------------------------------------

    @Test
    void saveSolution_retourneDTO_quandTourneeExiste() {
        preparerEtSauvegarderTournee(1, 120.0, 50.0);

        SolutionResponseDTO result = solutionService.saveSolution(preparerRequest("AlgoTest", Set.of(1), 1));

        assertNotNull(result);
    }

    @Test
    void saveSolution_persiste_enBase() {
        preparerEtSauvegarderTournee(2, 60.0, 40.0);

        solutionService.saveSolution(preparerRequest("AlgoPersist", Set.of(2), 1));

        assertEquals(1, solutionRepository.count());
    }

    @Test
    void saveSolution_calculeTempsTotalCorrectement() {
        preparerEtSauvegarderTournee(3, 100.0, 30.0);
        preparerEtSauvegarderTournee(4, 80.0,  20.0);

        SolutionResponseDTO result = solutionService.saveSolution(preparerRequest("AlgoTemps", Set.of(3, 4), 2));

        assertEquals(180.0, result.getTempsTotal(), 0.001);
    }

    @Test
    void saveSolution_calculeCoutTotalCorrectement() {
        // distanceTotale = 100km → cout = (100 * 8 / 100) * 1.85 = 14.8
        preparerEtSauvegarderTournee(5, 60.0, 100.0);

        SolutionResponseDTO result = solutionService.saveSolution(preparerRequest("AlgoCout", Set.of(5), 1));

        assertEquals((100.0 * 8.0 / 100.0) * 1.85, result.getCoutTotal(), 0.001);
    }

    @Test
    void saveSolution_gardeLesDeuxSolutions_quandAlgosDifferents() {
        preparerEtSauvegarderTournee(6, 60.0, 40.0);
        preparerEtSauvegarderTournee(7, 70.0, 50.0);

        solutionService.saveSolution(preparerRequest("AlgoA", Set.of(6), 1));
        solutionService.saveSolution(preparerRequest("AlgoB", Set.of(7), 1));

        assertEquals(2, solutionRepository.count());
    }
    @Test
    void saveSolution_lanceException_quandTourneeInexistante() {
        assertThrows(RuntimeException.class,
                () -> solutionService.saveSolution(preparerRequest("AlgoInvalid", Set.of(999), 1)));
    }

    // -----------------------------------------------------------------------
    // getAllSolutions()
    // -----------------------------------------------------------------------

    @Test
    void getAllSolutions_retourneListeVide_quandBaseVide() {
        assertTrue(solutionService.getAllSolutions().isEmpty());
    }

    @Test
    void getAllSolutions_retourneToutesLesSolutions() {
        preparerEtSauvegarderTournee(8,  60.0, 40.0);
        preparerEtSauvegarderTournee(9, 70.0, 50.0);

        solutionService.saveSolution(preparerRequest("Algo1", Set.of(8), 1));
        solutionService.saveSolution(preparerRequest("Algo2", Set.of(9), 1));

        assertEquals(2, solutionService.getAllSolutions().size());
    }

    // -----------------------------------------------------------------------
    // activerSolution()
    // -----------------------------------------------------------------------

    @Test
    void activerSolution_lanceException_quandSolutionInexistante() {
        assertThrows(RuntimeException.class, () -> solutionService.activerSolution(9999));
    }

    @Test
    void activerSolution_retourneDTO_quandSolutionExiste() {
        preparerEtSauvegarderTournee(10, 60.0, 40.0);
        solutionService.saveSolution(preparerRequest("AlgoActiver", Set.of(10), 1));
        int id = solutionRepository.findAll().get(0).getId();

        SolutionResponseDTO result = solutionService.activerSolution(id);

        assertNotNull(result);
    }

    // -----------------------------------------------------------------------
    // getSolutionById()
    // -----------------------------------------------------------------------

    @Test
    void getSolutionById_retourneDTO_quandExiste() {
        preparerEtSauvegarderTournee(11, 60.0, 40.0);
        solutionService.saveSolution(preparerRequest("AlgoGet", Set.of(11), 1));
        int id = solutionRepository.findAll().get(0).getId();

        assertNotNull(solutionService.getSolutionById(id));
    }

    @Test
    void getSolutionById_lanceException_quandInexistante() {
        assertThrows(RuntimeException.class, () -> solutionService.getSolutionById(9999));
    }

    // -----------------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------------

    private TourneeEntity preparerEtSauvegarderTournee(int id, double tempsTotal, double distanceTotale) {
        TourneeEntity tournee = new TourneeEntity();
        tournee.setIdTournee(id);
        tournee.setTempsTotal(tempsTotal);
        tournee.setDistanceTotale(distanceTotale);
        tournee.setStatut(StatutTournee.PLANIFIEE);
        tournee.setCommandes(Set.of());
        return tourneeRepository.save(tournee);
    }

    private SolutionRequest preparerRequest(String nomAlgo, Set<Integer> tourneesIds, int nbEquipes) {
        SolutionRequest request = new SolutionRequest();
        request.setNomAlgorithme(nomAlgo);
        request.setTournees_ids(tourneesIds);
        request.setNbEquipesUtilisees(nbEquipes);
        return request;
    }
}