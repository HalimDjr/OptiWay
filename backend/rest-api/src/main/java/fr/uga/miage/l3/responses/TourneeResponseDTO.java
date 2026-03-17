package fr.uga.miage.l3.responses;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


@Data
@Schema(description = "DTO représentant une tournée de livraison")
public class TourneeResponseDTO
{
@Schema(description = "id de la tournée ")
private int idTournee;
@Schema(description = "Temps total estimé de la tournée", example = "125.5")
private double tempsTotal;
@Schema(description = "Date de la tournée", example = "2025-04-15")
private Date dateTournee;
@Schema(description = "Heure de départ de la tournée", example = "08:30:00")
private Timestamp heureDepart;
@Schema(description = "Distance totale parcourue pendant la tournée en kilomètres", example = "42.7")
private double distanceTotale;

@Schema(description = "Numéro de l'équipe responsable de la tournée", example = "3")
private int numeroEquipe;

private Set<CommandeCoordDTO> commandes;
}
