package fr.uga.miage.l3.it.services;

import fr.uga.miage.l3.exceptions.rest.NotFoundEquipeResetException;
import fr.uga.miage.l3.models.EquipeEntity;
import fr.uga.miage.l3.repository.EquipeRepository;
import fr.uga.miage.l3.responses.EquipeResponseDTO;
import fr.uga.miage.l3.services.EquipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase
@SpringBootTest
class EquipeServiceTest {

    @Autowired
    private EquipeService equipeService;

    @Autowired
    private EquipeRepository equipeRepository;

    @BeforeEach
    void setUp() {
        equipeRepository.deleteAll();
    }

    // -----------------------------------------------------------------------
    // getNombreEquipes()
    // -----------------------------------------------------------------------

    @Test
    void getNombreEquipes_retourneZero_quandBaseVide() {
        assertEquals(0L, equipeService.getNombreEquipes());
    }

    @Test
    void getNombreEquipes_retourneLeBonNombre() {
        equipeRepository.save(preparerEquipe(1, 8.0));
        equipeRepository.save(preparerEquipe(2, 6.0));

        assertEquals(2L, equipeService.getNombreEquipes());
    }

    // -----------------------------------------------------------------------
    // getHeuresMax()
    // -----------------------------------------------------------------------

    @Test
    void getHeuresMax_lanceException_quandBaseVide() {
        assertThrows(NotFoundEquipeResetException.class, () -> equipeService.getHeuresMax());
    }

    @Test
    void getHeuresMax_retourneLaValeurMaximale() {
        equipeRepository.save(preparerEquipe(3, 6.0));
        equipeRepository.save(preparerEquipe(4, 10.0));
        equipeRepository.save(preparerEquipe(5, 8.0));

        assertEquals(10.0, equipeService.getHeuresMax(), 0.001);
    }

    @Test
    void getHeuresMax_retourneLaValeur_quandUneSeuleEquipe() {
        equipeRepository.save(preparerEquipe(6, 7.5));

        assertEquals(7.5, equipeService.getHeuresMax(), 0.001);
    }

    // -----------------------------------------------------------------------
    // getAllEquipes()
    // -----------------------------------------------------------------------

    @Test
    void getAllEquipes_retourneListeVide_quandBaseVide() {
        List<EquipeResponseDTO> result = equipeService.getAllEquipes();

        assertTrue(result.isEmpty());
    }

    @Test
    void getAllEquipes_retourneToutes_quandPlusieursEquipes() {
        equipeRepository.save(preparerEquipe(7, 8.0));
        equipeRepository.save(preparerEquipe(8, 6.0));

        assertEquals(2, equipeService.getAllEquipes().size());
    }

    // -----------------------------------------------------------------------
    // Helpers
    // -----------------------------------------------------------------------

    private EquipeEntity preparerEquipe(int numeroEquipe, double nbHeuresMax) {
        EquipeEntity equipe = new EquipeEntity();
        equipe.setNumeroEquipe(numeroEquipe);
        equipe.setNbHeuresMax(nbHeuresMax);
        return equipe;
    }
}