package fr.uga.miage.l3.controllers;

import fr.uga.miage.l3.responses.CommandResponseDTO;
import fr.uga.miage.l3.services.CommandeService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequiredArgsConstructor
public class CommandController {

    private final CommandeService commandeService;

    @GetMapping("/non-livres")
    public Set<CommandResponseDTO> getAllCommandesNonLivres() {
        return commandeService.getAllCommandesNonLivres();
    }

    @GetMapping("/nombre-non-livres")
    public Integer getNombreCommandesNonLivres() {
        return commandeService.getNombreCommandesNonLivres();
    }
}