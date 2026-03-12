package fr.uga.miage.l3.endpoints;

import fr.uga.miage.l3.request.TourneeRequest;
import fr.uga.miage.l3.responses.TourneeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Set;

@RequestMapping("/api/tournees")
public interface TourneeEndpoints {
    @Operation(description = "création d'une tournee")
    @ApiResponse(responseCode = "200",description = "créer une playlist")
    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/tournee")
    TourneeResponseDTO createTournee(@RequestParam TourneeRequest tourneeRequest);


    @Operation(description = "lister toutes les tournees d'une date donnée")
    @ApiResponse(responseCode = "200",description = "récupérer toutes les tournes d'une data")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{date}")
    Set<TourneeResponseDTO> getAllTournees(
                                           @PathVariable(name="date")
                                           @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                           LocalDate date
                                           );


}