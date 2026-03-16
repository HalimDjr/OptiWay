package fr.uga.miage.l3.endpoints;

import fr.uga.miage.l3.responses.EntrepotResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/api/entrepot")

public interface EntrepotEndpoint {
    @Operation(description="récupérer un entrepot selon son id")
    @ApiResponse(responseCode = "200", description = "entrepot récupéré avec succès")
    @ApiResponse(responseCode = "404", description = "id n'existe pas")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    EntrepotResponseDTO getEntrepotById(
            @PathVariable(name="id") int id
            );
}
