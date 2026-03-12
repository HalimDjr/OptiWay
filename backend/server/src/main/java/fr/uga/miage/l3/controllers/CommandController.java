package fr.uga.miage.l3.controllers;

import fr.uga.miage.l3.endpoints.CommandEndpoints;
import fr.uga.miage.l3.responses.CommandResponseDTO;
import fr.uga.miage.l3.services.CommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import java.util.Set;
@Controller
@RequiredArgsConstructor
public class CommandController implements CommandEndpoints {
    private final CommandeService commandeService;

    @Override
    public Set<CommandResponseDTO> getAllCommandesNonLivres(){
        return commandeService.getAllCommandesNonLivres();
    }

    /*
    * @todo
    * faire toutes les implémentaition des méthodes dans le endoint CommandEndpoint
    * */



}
