package fr.uga.miage.l3.controllers;

import fr.uga.miage.l3.endpoints.EntrepotEndpoint;
import fr.uga.miage.l3.responses.EntrepotResponseDTO;
import fr.uga.miage.l3.services.EntrepotService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/entrepot")
public class EntrepotController implements EntrepotEndpoint {
    private final EntrepotService entrepotService;
    @Override
    public EntrepotResponseDTO getEntrepotById(int id){
        return entrepotService.getEntrepotById(id);

    }
}
