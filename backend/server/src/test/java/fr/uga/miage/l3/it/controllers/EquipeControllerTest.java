package fr.uga.miage.l3.it.controllers;

import fr.uga.miage.l3.models.EquipeEntity;
import fr.uga.miage.l3.repository.EquipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.HashSet;

@AutoConfigureTestDatabase
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EquipeControllerTest {

    private static final String BASE_URI = "/api/equipes";

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        equipeRepository.deleteAll();
    }

    @Test
    @DisplayName("GET-200 nombre d'équipes égal à 0")
    void get_nombre_equipes_200_zero() {
        webTestClient
                .get()
                .uri(BASE_URI + "/nb-equipes")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Long.class)
                .isEqualTo(0L);
    }

    @Test
    @DisplayName("GET-200 nombre d'équipes")
    void get_nombre_equipes_200() {
        EquipeEntity equipe1 = new EquipeEntity();
        equipe1.setNumeroEquipe(1);
        equipe1.setNbHeuresMax(7.5);
        equipe1.setTournees(new HashSet<>());
        equipe1.setConducteur(null);
        equipe1.setManutentionnaires(new HashSet<>());
        equipe1.setVehicule(null);

        EquipeEntity equipe2 = new EquipeEntity();
        equipe2.setNumeroEquipe(2);
        equipe2.setNbHeuresMax(8.0);
        equipe2.setTournees(new HashSet<>());
        equipe2.setConducteur(null);
        equipe2.setManutentionnaires(new HashSet<>());
        equipe2.setVehicule(null);

        equipeRepository.save(equipe1);
        equipeRepository.save(equipe2);

        webTestClient
                .get()
                .uri(BASE_URI + "/nb-equipes")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Long.class)
                .isEqualTo(2L);
    }

    @Test
    @DisplayName("GET-500 heures max avec aucune équipe")
    void get_heures_max_500_when_no_equipe() {
        webTestClient
                .get()
                .uri(BASE_URI + "/heures-max")
                .exchange()
                .expectStatus()
                .is5xxServerError();
    }

    @Test
    @DisplayName("GET-200 heures max")
    void get_heures_max_200() {
        EquipeEntity equipe1 = new EquipeEntity();
        equipe1.setNumeroEquipe(1);
        equipe1.setNbHeuresMax(7.5);
        equipe1.setTournees(new HashSet<>());
        equipe1.setConducteur(null);
        equipe1.setManutentionnaires(new HashSet<>());
        equipe1.setVehicule(null);

        EquipeEntity equipe2 = new EquipeEntity();
        equipe2.setNumeroEquipe(2);
        equipe2.setNbHeuresMax(8.0);
        equipe2.setTournees(new HashSet<>());
        equipe2.setConducteur(null);
        equipe2.setManutentionnaires(new HashSet<>());
        equipe2.setVehicule(null);

        equipeRepository.save(equipe1);
        equipeRepository.save(equipe2);

        webTestClient
                .get()
                .uri(BASE_URI + "/heures-max")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Double.class)
                .isEqualTo(8.0);
    }

    @Test
    @DisplayName("GET-200 récupération de toutes les équipes vide")
    void get_all_equipes_200_empty() {
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
    @DisplayName("GET-200 récupération de toutes les équipes")
    void get_all_equipes_200() {
        EquipeEntity equipe1 = new EquipeEntity();
        equipe1.setNumeroEquipe(1);
        equipe1.setNbHeuresMax(7.5);
        equipe1.setTournees(new HashSet<>());
        equipe1.setConducteur(null);
        equipe1.setManutentionnaires(new HashSet<>());
        equipe1.setVehicule(null);

        EquipeEntity equipe2 = new EquipeEntity();
        equipe2.setNumeroEquipe(2);
        equipe2.setNbHeuresMax(8.0);
        equipe2.setTournees(new HashSet<>());
        equipe2.setConducteur(null);
        equipe2.setManutentionnaires(new HashSet<>());
        equipe2.setVehicule(null);

        equipeRepository.save(equipe1);
        equipeRepository.save(equipe2);

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
}