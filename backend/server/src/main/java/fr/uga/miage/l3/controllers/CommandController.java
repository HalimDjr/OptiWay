package fr.uga.miage.l3.controllers;

import fr.uga.miage.l3.endpoints.CommandEndpoints;
import fr.uga.miage.l3.responses.CommandResponseDTO;
import fr.uga.miage.l3.services.CommandeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
@RestController
@RequiredArgsConstructor
public class CommandController implements CommandEndpoints {
    private final CommandeService commandeService;

    @Override
    public Set<CommandResponseDTO> getAllCommandesNonLivres(){
        return commandeService.getAllCommandesNonLivres();
    }

    @Override
    public CommandResponseDTO getCommandeById(String commandId) {
        System.out.println("command_id= "+commandId);
        CommandResponseDTO commandResponseDTO=commandeService.getCommandeById(commandId);
        System.out.println("------------------------------------controlelr----------------------------------------");
        System.out.println("command_id_dto_controller= "+commandResponseDTO.getNumeroCommande());
        System.out.println("numeroCommande = " + commandResponseDTO.getNumeroCommande());
        System.out.println("dateLimite      = " + commandResponseDTO.getDateLimite());
        System.out.println("status          = " + commandResponseDTO.getStatus());
        System.out.println("poids           = " + commandResponseDTO.getPoids());
        System.out.println("volume          = " + commandResponseDTO.getVolume());
        return commandResponseDTO;
    }


    /*
    * @todo
    * faire toutes les implémentaition des méthodes dans le endoint CommandEndpoint
    * */



}
