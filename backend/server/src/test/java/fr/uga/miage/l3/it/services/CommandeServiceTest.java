package fr.uga.miage.l3.it.services;

import fr.uga.miage.l3.enums.StatutCommande;
import fr.uga.miage.l3.models.AdresseEntity;
import fr.uga.miage.l3.models.CommandeEntity;
import fr.uga.miage.l3.repository.AdresseRepository;
import fr.uga.miage.l3.repository.CommandeRepository;
import fr.uga.miage.l3.responses.CommandResponseDTO;
import fr.uga.miage.l3.services.CommandeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase
@SpringBootTest
class CommandeServiceTest {

    @Autowired
    private CommandeService commandeService;

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private AdresseRepository adresseRepository; // ⚠️ à créer si inexistant (voir bas du fichier)

    @BeforeEach
    void setUp() {
        commandeRepository.deleteAll();
        adresseRepository.deleteAll();
    }

    // -----------------------------------------------------------------------
    // getAllCommandesNonLivres()
    // -----------------------------------------------------------------------

    @Test
    void getAllCommandesNonLivres_retourneCommandes_quandStatutNonPlanifiee() {
        commandeRepository.save(preparerCommande("CMD-001", StatutCommande.NON_PLANIFIEE));

        Set<CommandResponseDTO> result = commandeService.getAllCommandesNonLivres();

        assertFalse(result.isEmpty());
    }

    @Test
    void getAllCommandesNonLivres_retourneCommandes_quandStatutAnnulee() {
        commandeRepository.save(preparerCommande("CMD-002", StatutCommande.ANNULEE));

        Set<CommandResponseDTO> result = commandeService.getAllCommandesNonLivres();

        assertFalse(result.isEmpty());
    }

    @Test
    void getAllCommandesNonLivres_retourneSetVide_quandSeulementLivree() {
        commandeRepository.save(preparerCommande("CMD-003", StatutCommande.LIVREE));

        Set<CommandResponseDTO> result = commandeService.getAllCommandesNonLivres();

        assertTrue(result.isEmpty());
    }

    @Test
    void getAllCommandesNonLivres_retourneSetVide_quandSeulementPlanifiee() {
        commandeRepository.save(preparerCommande("CMD-004", StatutCommande.PLANIFIEE));

        Set<CommandResponseDTO> result = commandeService.getAllCommandesNonLivres();

        assertTrue(result.isEmpty());
    }

    @Test
    void getAllCommandesNonLivres_retourneSetVide_quandBaseVide() {
        Set<CommandResponseDTO> result = commandeService.getAllCommandesNonLivres();

        assertTrue(result.isEmpty());
    }

    @Test
    void getAllCommandesNonLivres_retourneSeulementNonPlanifieeEtAnnulee_quandMix() {
        commandeRepository.save(preparerCommande("CMD-005", StatutCommande.NON_PLANIFIEE));
        commandeRepository.save(preparerCommande("CMD-006", StatutCommande.ANNULEE));
        commandeRepository.save(preparerCommande("CMD-007", StatutCommande.LIVREE));
        commandeRepository.save(preparerCommande("CMD-008", StatutCommande.PLANIFIEE));

        Set<CommandResponseDTO> result = commandeService.getAllCommandesNonLivres();

        assertEquals(2, result.size());
    }

    @Test
    void getAllCommandesNonLivres_statutCorrectementMappe() {
        commandeRepository.save(preparerCommande("CMD-009", StatutCommande.NON_PLANIFIEE));

        Set<CommandResponseDTO> result = commandeService.getAllCommandesNonLivres();

        assertEquals(StatutCommande.NON_PLANIFIEE.toString(), result.iterator().next().getStatus());
    }

    @Test
    void getAllCommandesNonLivres_coordonneesCorrectementMappees() {
        commandeRepository.save(preparerCommandeAvecCoordonnees("CMD-010", StatutCommande.NON_PLANIFIEE, 45.18, 5.72));

        CommandResponseDTO dto = commandeService.getAllCommandesNonLivres().iterator().next();

        assertEquals(45.18, dto.getLatitude(),   0.0001);
        assertEquals(5.72,  dto.getLongtitude(), 0.0001);
    }

    // -----------------------------------------------------------------------
    // getNombreCommandesNonLivres()
    // -----------------------------------------------------------------------

    @Test
    void getNombreCommandesNonLivres_retourneLeBonNombre() {
        commandeRepository.save(preparerCommande("CMD-011", StatutCommande.NON_PLANIFIEE));
        commandeRepository.save(preparerCommande("CMD-012", StatutCommande.ANNULEE));

        assertEquals(2, commandeService.getNombreCommandesNonLivres());
    }

    @Test
    void getNombreCommandesNonLivres_retourneZero_quandBaseVide() {
        assertEquals(0, commandeService.getNombreCommandesNonLivres());
    }

    @Test
    void getNombreCommandesNonLivres_neComptePas_lesCommandesLivrees() {
        commandeRepository.save(preparerCommande("CMD-013", StatutCommande.NON_PLANIFIEE));
        commandeRepository.save(preparerCommande("CMD-014", StatutCommande.LIVREE));

        assertEquals(1, commandeService.getNombreCommandesNonLivres());
    }

    // -----------------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------------

    private CommandeEntity preparerCommande(String id, StatutCommande statut) {
        return preparerCommandeAvecCoordonnees(id, statut, 45.188529, 5.724524);
    }

    private CommandeEntity preparerCommandeAvecCoordonnees(String id, StatutCommande statut,
                                                           double lat, double lon) {
        AdresseEntity adresse = new AdresseEntity();
        adresse.setId((long) id.hashCode());
        adresse.setLatitude(lat);
        adresse.setLongitude(lon);
        adresse.setNumeroRue(1);
        adresse.setRue("rue test");
        adresse.setVille("Grenoble");
        adresse.setCodePostal(38000);
        adresse.setPays("France");
        adresseRepository.save(adresse); // ← sauvegarde obligatoire (pas de cascade)

        CommandeEntity commande = new CommandeEntity();
        commande.setNumeroCommande(id);
        commande.setStatut(statut);
        commande.setAdresse(adresse);
        commande.setDateLimite(new Date());
        commande.setPoids(1.0);
        commande.setVolume(1.0);
        return commande;
    }
}

/*
 * ⚠️  Si AdresseRepository n'existe pas dans ton projet, crée ce fichier :
 *
 * package fr.uga.miage.l3.repository;
 * import fr.uga.miage.l3.models.AdresseEntity;
 * import org.springframework.data.jpa.repository.JpaRepository;
 * public interface AdresseRepository extends JpaRepository<AdresseEntity, Long> {}
 */