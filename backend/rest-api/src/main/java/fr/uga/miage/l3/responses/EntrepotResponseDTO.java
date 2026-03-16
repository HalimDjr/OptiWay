package fr.uga.miage.l3.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "DTO représentant un entrepot")
public class EntrepotResponseDTO {
    @Schema(description = "id de l'entrepot")
    private int id;
    @Schema(description = "nom de l'entrepot")
    private String nom;
    @Schema(description="latitude")
    private double latitude;

    @Schema (description="longtitude")
    private double longtitude;


}
