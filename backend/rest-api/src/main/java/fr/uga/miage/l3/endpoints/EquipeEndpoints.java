package fr.uga.miage.l3.endpoints;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
/// ///
@RequestMapping("/api/equipes")
@Tag(name = "Equipes", description = "Gestion des équipes")
public interface EquipeEndpoints {
    @Operation(description = "Récupérer le nombre d'équipes")
    @ApiResponse(responseCode = "200",description = "succes")
    @GetMapping("/nb-equipes")
    Long getNombreEquipes();


    @Operation(description = "Récupérer le nombre d'heures max")
    @ApiResponse(responseCode = "200",description = "succes")
    @GetMapping("/heures-max")
    double getHeuresMax();


}
