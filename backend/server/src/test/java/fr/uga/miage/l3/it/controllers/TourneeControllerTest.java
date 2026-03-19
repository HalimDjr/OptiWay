package fr.uga.miage.l3.it.controllers;

import fr.uga.miage.l3.enums.StatutCommande;
import fr.uga.miage.l3.models.AdresseEntity;
import fr.uga.miage.l3.models.CommandeEntity;
import fr.uga.miage.l3.models.EquipeEntity;
import fr.uga.miage.l3.repository.AdresseRepository;
import fr.uga.miage.l3.repository.CommandeRepository;
import fr.uga.miage.l3.repository.EquipeRepository;
import fr.uga.miage.l3.repository.TourneeRepository;
import fr.uga.miage.l3.request.TourneeRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AutoConfigureTestDatabase
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TourneeControllerTest {

    private static final String BASE_URI = "/api/tournees";

    @Autowired
    private TourneeRepository tourneeRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private AdresseRepository adresseRepository;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        tourneeRepository.deleteAll();
        commandeRepository.deleteAll();
        equipeRepository.deleteAll();
        adresseRepository.deleteAll();
    }

    @Test
    @DisplayName("POST-200 création d'une tournée")
    void create_tournee_200() {
        EquipeEntity equipe = new EquipeEntity();
        equipe.setNumeroEquipe(1);
        equipe.setNbHeuresMax(8.0);
        equipe.setTournees(new HashSet<>());
        equipe.setConducteur(null);
        equipe.setManutentionnaires(new HashSet<>());
        equipe.setVehicule(null);
        equipeRepository.save(equipe);

        AdresseEntity adresse = new AdresseEntity();
        adresse.setId(111L);
        adresse.setLatitude(36.75);
        adresse.setLongitude(3.06);
        adresseRepository.save(adresse);

        CommandeEntity commande = new CommandeEntity();
        commande.setNumeroCommande("C1");
        commande.setDateLimite(new Date());
        commande.setStatut(StatutCommande.NON_PLANIFIEE);
        commande.setPoids(10.0);
        commande.setVolume(5.0);
        commande.setAdresse(adresse);
        commande.setProduits(new HashSet<>());
        commande.setTournees(new HashSet<>());
        commandeRepository.save(commande);

        TourneeRequest request = new TourneeRequest();
        request.setIdTournee(10);
        request.setTempsTotal(120.0);
        request.setDateTournee(new Date());
        request.setHeureDepart(Timestamp.valueOf(LocalDateTime.of(2026, 3, 19, 8, 0)));
        request.setDistanceTotale(50.0);
        request.setStatutTournee(1);
        request.setCommandes_ids(Set.of("C1"));
        request.setNumeroEquipe(1);

        webTestClient
                .post()
                .uri(BASE_URI + "/tournee")
                .bodyValue(request)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.idTournee").isEqualTo(10);
    }
}