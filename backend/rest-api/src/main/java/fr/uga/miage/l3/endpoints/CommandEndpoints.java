package fr.uga.miage.l3.endpoints;

import fr.uga.miage.l3.responses.CommandResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Set;

@RequestMapping("/api/commands")

public interface CommandEndpoints {
    @Operation(description="récupérer toutes les commandes avec le status non livrés")
    @ApiResponse(responseCode = "200",description="Les commandes sont récupéres ")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/non-livres")
    Set<CommandResponseDTO> getAllCommandesNonLivres();

    @Operation(description="récupérer une commande par son id")
    @ApiResponse(responseCode = "200",description="Les commandes sont récupéres ")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{commandId}")
    CommandResponseDTO getCommandeById(@PathVariable("commandId") String commandId);



    /*@Operation(description = "le nombre de commandes non livrés")
    @ApiResponse(responseCode = "200",description = "le nombre de comandes non livrés est trouvé ")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/non-livres/count")
    Long getNombreCommandesNonLivres();*/
    /*
    * c'est commenté car CommandEndpoint doit l'implémenter ,sinon erreur de compilation
    * */

}
