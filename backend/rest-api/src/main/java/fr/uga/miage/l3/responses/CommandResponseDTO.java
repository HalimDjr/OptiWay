package fr.uga.miage.l3.responses;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class CommandResponseDTO {
    @Schema(description = "numero de la commande")
    private String numeroCommande;

    @Schema(description = "datelimite de livraison")
    private Date dateLimite;

    @Schema(description ="status de la commande")
    private String status;

    @Schema(description="poids de la commande")
    private double poids;



    @Schema(description = "volume de la commande")
    private double volume;

    @Schema(description="latitude de la commande")
    private double latitude;

    @Schema(description="longtitude de la commande")
    private double longtitude;

}
