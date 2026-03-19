package fr.uga.miage.l3.it.controllers;

import fr.uga.miage.l3.models.SolutionEntity;
import fr.uga.miage.l3.repository.SolutionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Date;
import java.util.HashSet;

@AutoConfigureTestDatabase
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SolutionControllerTest {

    private static final String BASE_URI = "/api/solutions";

    @Autowired
    private SolutionRepository solutionRepository;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        solutionRepository.deleteAll();
    }

    @Test
    @DisplayName("GET-200 récupération de toutes les solutions vide")
    void get_all_solutions_200_empty() {
        webTestClient
                .get()
                .uri(BASE_URI)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json("[]");
    }

    @Test
    @DisplayName("GET-200 récupération de toutes les solutions")
    void get_all_solutions_200() {
        SolutionEntity solution1 = new SolutionEntity();
        solution1.setNomAlgorithme("Algorithme A");
        solution1.setDate(new Date());
        solution1.setActivee(false);
        solution1.setNbCommandesLivrees(10);
        solution1.setTempsTotal(120.0);
        solution1.setDistanceTotale(50.0);
        solution1.setCoutTotal(300.0);
        solution1.setNbEquipesUtilisees(2);
        solution1.setTournees(new HashSet<>());

        SolutionEntity solution2 = new SolutionEntity();
        solution2.setNomAlgorithme("Algorithme B");
        solution2.setDate(new Date());
        solution2.setActivee(true);
        solution2.setNbCommandesLivrees(12);
        solution2.setTempsTotal(100.0);
        solution2.setDistanceTotale(45.0);
        solution2.setCoutTotal(280.0);
        solution2.setNbEquipesUtilisees(3);
        solution2.setTournees(new HashSet<>());

        solutionRepository.save(solution1);
        solutionRepository.save(solution2);

        webTestClient
                .get()
                .uri(BASE_URI)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$.length()").isEqualTo(2);
    }

    @Test
    @DisplayName("GET-200 récupération d'une solution par id")
    void get_solution_by_id_200() {
        SolutionEntity solution = new SolutionEntity();
        solution.setNomAlgorithme("Algorithme A");
        solution.setDate(new Date());
        solution.setActivee(false);
        solution.setNbCommandesLivrees(10);
        solution.setTempsTotal(120.0);
        solution.setDistanceTotale(50.0);
        solution.setCoutTotal(300.0);
        solution.setNbEquipesUtilisees(2);
        solution.setTournees(new HashSet<>());

        SolutionEntity savedSolution = solutionRepository.save(solution);

        webTestClient
                .get()
                .uri(BASE_URI + "/{id}", savedSolution.getId())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(savedSolution.getId());
    }

    @Test
    @DisplayName("GET-500 récupération d'une solution inexistante")
    void get_solution_by_id_500() {
        webTestClient
                .get()
                .uri(BASE_URI + "/{id}", 999)
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

    @Test
    @DisplayName("PUT-200 activation d'une solution")
    void activer_solution_200() {
        SolutionEntity solution = new SolutionEntity();
        solution.setNomAlgorithme("Algorithme A");
        solution.setDate(new Date());
        solution.setActivee(false);
        solution.setNbCommandesLivrees(10);
        solution.setTempsTotal(120.0);
        solution.setDistanceTotale(50.0);
        solution.setCoutTotal(300.0);
        solution.setNbEquipesUtilisees(2);
        solution.setTournees(new HashSet<>());

        SolutionEntity savedSolution = solutionRepository.save(solution);

        webTestClient
                .put()
                .uri(BASE_URI + "/{id}/activer", savedSolution.getId())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.id").isEqualTo(savedSolution.getId());
    }

    @Test
    @DisplayName("GET-500 récupération d'une solution inexistante")
    void get_solution_nonexistante_by_id_500() {
        webTestClient
                .get()
                .uri(BASE_URI + "/{id}", 999)
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }
}