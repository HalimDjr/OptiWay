package fr.uga.miage.l3.it.services;

import fr.uga.miage.l3.exceptions.rest.NotFoundEntrepotRestException;
import fr.uga.miage.l3.models.AdresseEntity;
import fr.uga.miage.l3.models.EntrepotEntity;
import fr.uga.miage.l3.repository.AdresseRepository;
import fr.uga.miage.l3.repository.EntrepotRepository;
import fr.uga.miage.l3.responses.EntrepotResponseDTO;
import fr.uga.miage.l3.services.EntrepotService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase
@SpringBootTest
class EntrepotServiceTest {

    @Autowired
    private EntrepotService entrepotService;

    @Autowired
    private EntrepotRepository entrepotRepository;

    @Autowired
    private AdresseRepository adresseRepository;

    @BeforeEach
    void setUp() {
        entrepotRepository.deleteAll();
        adresseRepository.deleteAll();
    }

    // -----------------------------------------------------------------------
    // getEntrepotById()
    // -----------------------------------------------------------------------

    @Test
    void getEntrepotById_retourneDTO_quandEntrepotExiste() {
        entrepotRepository.save(preparerEntrepot(1, "Entrepot Central", 45.18, 5.72));

        EntrepotResponseDTO result = entrepotService.getEntrepotById(1);

        assertNotNull(result);
    }

    @Test
    void getEntrepotById_coordonneesCorrectementMappees() {
        entrepotRepository.save(preparerEntrepot(2, "Entrepot Sud", 45.18, 5.72));

        EntrepotResponseDTO result = entrepotService.getEntrepotById(2);

        assertEquals(45.18, result.getLatitude(),   0.0001);
        assertEquals(5.72,  result.getLongtitude(), 0.0001);
    }

    @Test
    void getEntrepotById_lanceNotFound_quandEntrepotInexistant() {
        assertThrows(NotFoundEntrepotRestException.class, () -> entrepotService.getEntrepotById(999));
    }

    @Test
    void getEntrepotById_retourneLeBonEntrepot_quandPlusieursEnBase() {
        entrepotRepository.save(preparerEntrepot(3, "Entrepot A", 45.0, 5.0));
        entrepotRepository.save(preparerEntrepot(4, "Entrepot B", 46.0, 6.0));

        EntrepotResponseDTO a = entrepotService.getEntrepotById(3);
        EntrepotResponseDTO b = entrepotService.getEntrepotById(4);

        assertNotEquals(a.getLatitude(), b.getLatitude());
    }

    // -----------------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------------

    private EntrepotEntity preparerEntrepot(int id, String nom, double lat, double lon) {
        AdresseEntity adresse = new AdresseEntity();
        adresse.setId((long) id * 100);
        adresse.setLatitude(lat);
        adresse.setLongitude(lon);
        adresse.setNumeroRue(1);
        adresse.setRue("rue test");
        adresse.setVille("Grenoble");
        adresse.setCodePostal(38000);
        adresse.setPays("France");
        adresseRepository.save(adresse);

        EntrepotEntity entrepot = new EntrepotEntity();
        entrepot.setId(id);
        entrepot.setNom(nom);
        entrepot.setAdresse(adresse);
        return entrepot;
    }
}