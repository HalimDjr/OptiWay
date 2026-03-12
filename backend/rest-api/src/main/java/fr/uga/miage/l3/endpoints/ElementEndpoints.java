package fr.uga.miage.l3.endpoints;

import fr.uga.miage.l3.request.ElementRequest;
import fr.uga.miage.l3.errors.ErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/element")
public interface ElementEndpoints {

    @Operation(description = "récupération d'un élement")
    @ApiResponse(responseCode = "201", description = "L'élement est créé")
    @ApiResponse(responseCode = "400", description = "La requête n'est pas conforme au attentes du serveurs", content = @Content(schema = @Schema(implementation = ErrorDTO.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    String create(@RequestBody ElementRequest request);

    @Operation(description = "récupération d'un élement")
    @ApiResponse(responseCode = "200", description = "L'élement est récupérer")
    @ApiResponse(responseCode = "404", description = "L'élement n'est pas trouvée", content = @Content(schema = @Schema(implementation = ErrorDTO.class), mediaType = MediaType.APPLICATION_JSON_VALUE))
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    String element(@RequestParam String name);

}
