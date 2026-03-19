package fr.uga.miage.l3.it.controllers;

import fr.uga.miage.l3.models.AdresseEntity;
import fr.uga.miage.l3.models.EntrepotEntity;
import fr.uga.miage.l3.repository.AdresseRepository;
import fr.uga.miage.l3.repository.CommandeRepository;
import fr.uga.miage.l3.repository.EntrepotRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient;

@AutoConfigureTestDatabase
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EntrepotControllerTest {

    private static final String BASE_URI = "/api/entrepot";

    @Autowired
    private EntrepotRepository entrepotRepository;

    @Autowired
    private AdresseRepository adresseRepository;

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        commandeRepository.deleteAll();
        entrepotRepository.deleteAll();
        adresseRepository.deleteAll();
    }

    @Test
    @DisplayName("GET-200 récupération d'un entrepot par id")
    void get_entrepot_by_id_200() {
        AdresseEntity adresse = new AdresseEntity();
        adresse.setId(111L);
        adresse.setLatitude(36.75);
        adresse.setLongitude(3.06);
        adresseRepository.save(adresse);

        EntrepotEntity entrepot = new EntrepotEntity();
        entrepot.setId(1);
        entrepot.setNom("Entrepot central");
        entrepot.setAdresse(adresse);
        entrepotRepository.save(entrepot);

        webTestClient
                .get()
                .uri(BASE_URI + "/{id}", entrepot.getId())
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.nom").isEqualTo("Entrepot central");
    }

    @Test
    @DisplayName("GET-404 récupération d'un entrepot inexistant")
    void get_entrepot_by_id_404() {
        webTestClient
                .get()
                .uri(BASE_URI + "/{id}", 999)
                .exchange()
                .expectStatus()
                .isNotFound(); // ← 404 au lieu de 500
    }
}