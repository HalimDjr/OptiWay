package fr.uga.miage.l3.it.controllers;

import fr.uga.miage.l3.enums.StatutCommande;
import fr.uga.miage.l3.models.AdresseEntity;
import fr.uga.miage.l3.models.CommandeEntity;
import fr.uga.miage.l3.repository.AdresseRepository;
import fr.uga.miage.l3.repository.CommandeRepository;
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

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommandControllerTest {

    private static final String BASE_URI = "/api/commands";

    @Autowired
    private CommandeRepository commandeEntityRepository;

    @Autowired
    private AdresseRepository adresseRepository;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        commandeEntityRepository.deleteAll();
        adresseRepository.deleteAll();
    }

    @Test
    @DisplayName("GET-200 récupération des commandes non livrées vide")
    void get_all_commandes_non_livrees_200_empty() {
        webTestClient
                .get()
                .uri(BASE_URI + "/non-livres")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .json("[]");
    }

    @Test
    @DisplayName("GET-200 nombre de commandes non livrées égal à 0")
    void get_nombre_commandes_non_livrees_200_zero() {
        webTestClient
                .get()
                .uri(BASE_URI + "/non-livres/count")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Integer.class)
                .isEqualTo(0);
    }

    @Test
    void get_nombre_commandes_non_livrees_200() {
        AdresseEntity adresse = new AdresseEntity();
        adresse.setId(1234L);
        adresse.setLatitude(36.75);
        adresse.setLongitude(3.06);
        adresseRepository.save(adresse);

        CommandeEntity commande1 = new CommandeEntity();
        commande1.setNumeroCommande("C1");
        commande1.setDateLimite(new Date());
        commande1.setStatut(StatutCommande.NON_PLANIFIEE);
        commande1.setPoids(10.0);
        commande1.setVolume(5.0);
        commande1.setAdresse(adresse);
        commande1.setProduits(new HashSet<>());
        commande1.setTournees(new HashSet<>());

        CommandeEntity commande2 = new CommandeEntity();
        commande2.setNumeroCommande("C2");
        commande2.setDateLimite(new Date());
        commande2.setStatut(StatutCommande.ANNULEE);
        commande2.setPoids(20.0);
        commande2.setVolume(8.0);
        commande2.setAdresse(adresse);
        commande2.setProduits(new HashSet<>());
        commande2.setTournees(new HashSet<>());

        CommandeEntity commande3 = new CommandeEntity();
        commande3.setNumeroCommande("C3");
        commande3.setDateLimite(new Date());
        commande3.setStatut(StatutCommande.LIVREE);
        commande3.setPoids(12.0);
        commande3.setVolume(6.0);
        commande3.setAdresse(adresse);
        commande3.setProduits(new HashSet<>());
        commande3.setTournees(new HashSet<>());

        commandeEntityRepository.saveAndFlush(commande1);
        commandeEntityRepository.saveAndFlush(commande2);
        commandeEntityRepository.saveAndFlush(commande3);

        var all = commandeEntityRepository.findAll();
        System.out.println("ALL SIZE = " + all.size());
        all.forEach(c -> System.out.println(c.getNumeroCommande() + " -> " + c.getStatut()));

        var filtered = commandeEntityRepository.getNonPlanifieesOuAnnulees();
        System.out.println("FILTERED SIZE = " + filtered.size());
        filtered.forEach(c -> System.out.println("FOUND " + c.getNumeroCommande() + " -> " + c.getStatut()));

        assertThat(all).hasSize(3);
        assertThat(filtered).hasSize(2);

        webTestClient
                .get()
                .uri(BASE_URI + "/non-livres/count")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Integer.class)
                .isEqualTo(2);
    }

    @Test
    @DisplayName("GET-200 récupération des commandes non livrées")
    void get_all_commandes_non_livrees_200() {
        AdresseEntity adresse = new AdresseEntity();
        adresse.setId(1234L);
        adresse.setLatitude(36.75);
        adresse.setLongitude(3.06);
        adresseRepository.save(adresse);

        CommandeEntity commande1 = new CommandeEntity();
        commande1.setNumeroCommande("C1");
        commande1.setDateLimite(new Date());
        commande1.setStatut(StatutCommande.NON_PLANIFIEE);
        commande1.setPoids(10.0);
        commande1.setVolume(5.0);
        commande1.setAdresse(adresse);
        commande1.setProduits(new HashSet<>());
        commande1.setTournees(new HashSet<>());

        CommandeEntity commande2 = new CommandeEntity();
        commande2.setNumeroCommande("C2");
        commande2.setDateLimite(new Date());
        commande2.setStatut(StatutCommande.ANNULEE);
        commande2.setPoids(20.0);
        commande2.setVolume(8.0);
        commande2.setAdresse(adresse);
        commande2.setProduits(new HashSet<>());
        commande2.setTournees(new HashSet<>());

        CommandeEntity commande3 = new CommandeEntity();
        commande3.setNumeroCommande("C3");
        commande3.setDateLimite(new Date());
        commande3.setStatut(StatutCommande.LIVREE);
        commande3.setPoids(12.0);
        commande3.setVolume(6.0);
        commande3.setAdresse(adresse);
        commande3.setProduits(new HashSet<>());
        commande3.setTournees(new HashSet<>());

        commandeEntityRepository.save(commande1);
        commandeEntityRepository.save(commande2);
        commandeEntityRepository.save(commande3);

        assertThat(commandeEntityRepository.findAll()).hasSize(3);

        commandeEntityRepository.findAll().forEach(c ->
                System.out.println(c.getNumeroCommande() + " -> " + c.getStatut())
        );

        webTestClient
                .get()
                .uri(BASE_URI + "/non-livres")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$").isArray()
                .jsonPath("$.length()").isEqualTo(2);
    }
}