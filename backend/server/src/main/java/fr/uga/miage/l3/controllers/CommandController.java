package fr.uga.miage.l3.controllers;

import fr.uga.miage.l3.endpoints.CommandEndpoints;
import fr.uga.miage.l3.responses.CommandResponseDTO;
import fr.uga.miage.l3.services.CommandeService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class CommandController implements CommandEndpoints {

    private final CommandeService commandeService;

    @Override
    public Set<CommandResponseDTO> getAllCommandesNonLivres() {
        return commandeService.getAllCommandesNonLivres();
    }

    @Override
    public Integer getNombreCommandesNonLivres() {
        return commandeService.getNombreCommandesNonLivres();
    }
}