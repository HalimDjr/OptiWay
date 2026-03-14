package fr.uga.miage.l3.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.sql.Timestamp;
import java.util.Set;
@Data
public class TourneeRequest {
    private int idTournee;
    private double tempsTotal;

    // format ISO8601 pour que Spring Jackson puisse parser
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private java.util.Date dateTournee;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private Timestamp heureDepart;

    private double distanceTotale;
    private int statutTournee;
    private Set<String> commandes_ids;
    private int numeroEquipe;
}
