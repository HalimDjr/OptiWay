package fr.uga.miage.l3.it.services;

import fr.uga.miage.l3.enums.StatutCommande;
import fr.uga.miage.l3.exceptions.technical.NotFoundCommandException;
import fr.uga.miage.l3.exceptions.technical.StatutTourneeException;
import fr.uga.miage.l3.models.AdresseEntity;
import fr.uga.miage.l3.models.CommandeEntity;
import fr.uga.miage.l3.models.EquipeEntity;
import fr.uga.miage.l3.repository.AdresseRepository;
import fr.uga.miage.l3.repository.CommandeRepository;
import fr.uga.miage.l3.repository.EquipeRepository;
import fr.uga.miage.l3.repository.TourneeRepository;
import fr.uga.miage.l3.request.TourneeRequest;
import fr.uga.miage.l3.responses.TourneeResponseDTO;
import fr.uga.miage.l3.services.TourneeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase
@SpringBootTest
class TourneeServiceTest {

    @Autowired
    private TourneeService tourneeService;

    @Autowired
    private TourneeRepository tourneeRepository;

    @Autowired
    private EquipeRepository equipeRepository;

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private AdresseRepository adresseRepository;

    @BeforeEach
    void setUp() {
        tourneeRepository.deleteAll();
        commandeRepository.deleteAll();
        adresseRepository.deleteAll();
        equipeRepository.deleteAll();
    }

    // -----------------------------------------------------------------------
    // createTournee()
    // -----------------------------------------------------------------------

    @Test
    void createTournee_retourneDTO_quandDonneesValides() {
        EquipeEntity equipe     = preparerEtSauvegarderEquipe(1);
        CommandeEntity commande = preparerEtSauvegarderCommande("CMD-001");

        TourneeResponseDTO result = tourneeService.createTournee(
                preparerRequest(100, 120.0, 50.0, 2, equipe.getNumeroEquipe(), Set.of(commande.getNumeroCommande()))
        );

        assertNotNull(result);
    }

    @Test
    void createTournee_persiste_enBase() {
        EquipeEntity equipe     = preparerEtSauvegarderEquipe(2);
        CommandeEntity commande = preparerEtSauvegarderCommande("CMD-002");

        tourneeService.createTournee(
                preparerRequest(101, 90.0, 40.0, 1, equipe.getNumeroEquipe(), Set.of(commande.getNumeroCommande()))
        );

        assertTrue(tourneeRepository.findById(101).isPresent());
    }

    @Test
    void createTournee_lanceException_quandStatutInvalide() {
        EquipeEntity equipe     = preparerEtSauvegarderEquipe(3);
        CommandeEntity commande = preparerEtSauvegarderCommande("CMD-003");

        assertThrows(StatutTourneeException.class, () ->
                tourneeService.createTournee(
                        preparerRequest(102, 60.0, 30.0, 99, equipe.getNumeroEquipe(), Set.of(commande.getNumeroCommande()))
                )
        );
    }

    @Test
    void createTournee_lanceException_quandEquipeInexistante() {
        CommandeEntity commande = preparerEtSauvegarderCommande("CMD-004");

        assertThrows(RuntimeException.class, () ->
                tourneeService.createTournee(
                        preparerRequest(103, 60.0, 30.0, 2, 9999, Set.of(commande.getNumeroCommande()))
                )
        );
    }

    @Test
    void createTournee_lanceException_quandCommandeInexistante() {
        EquipeEntity equipe = preparerEtSauvegarderEquipe(4);

        assertThrows(NotFoundCommandException.class, () ->
                tourneeService.createTournee(
                        preparerRequest(104, 60.0, 30.0, 2, equipe.getNumeroEquipe(), Set.of("CMD-INEXISTANTE"))
                )
        );
    }

    // -----------------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------------

    private EquipeEntity preparerEtSauvegarderEquipe(int numeroEquipe) {
        EquipeEntity equipe = new EquipeEntity();
        equipe.setNumeroEquipe(numeroEquipe);
        equipe.setNbHeuresMax(8.0);
        return equipeRepository.save(equipe);
    }

    private CommandeEntity preparerEtSauvegarderCommande(String numeroCommande) {
        AdresseEntity adresse = new AdresseEntity();
        adresse.setId((long) numeroCommande.hashCode());
        adresse.setLatitude(45.18);
        adresse.setLongitude(5.72);
        adresse.setNumeroRue(1);
        adresse.setRue("rue test");
        adresse.setVille("Grenoble");
        adresse.setCodePostal(38000);
        adresse.setPays("France");
        adresseRepository.save(adresse);

        CommandeEntity commande = new CommandeEntity();
        commande.setNumeroCommande(numeroCommande);
        commande.setStatut(StatutCommande.NON_PLANIFIEE);
        commande.setAdresse(adresse);
        commande.setDateLimite(new Date());
        commande.setPoids(1.0);
        commande.setVolume(1.0);
        return commandeRepository.save(commande);
    }

    private TourneeRequest preparerRequest(int idTournee, double tempsTotal, double distanceTotale,
                                           int statutTournee, int numeroEquipe, Set<String> commandesIds) {
        TourneeRequest request = new TourneeRequest();
        request.setIdTournee(idTournee);
        request.setTempsTotal(tempsTotal);
        request.setDistanceTotale(distanceTotale);
        request.setStatutTournee(statutTournee);
        request.setNumeroEquipe(numeroEquipe);
        request.setCommandes_ids(commandesIds);
        request.setHeureDepart(new Timestamp(System.currentTimeMillis()));
        return request;
    }
}