package fr.uga.miage.l3.request;

import fr.uga.miage.l3.responses.CommandResponseDTO;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;
@Data
public class TourneeRequest {
    private int idTournee;
    private double tempsTotal;
    private Date dateTournee;
    private Timestamp heureDepart;
    private double distanceTotale;
    private String statutTournee;
    private Set<CommandResponseDTO> commandes;
    private int numeroEquipe;
}
