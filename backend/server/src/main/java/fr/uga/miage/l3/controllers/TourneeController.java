package fr.uga.miage.l3.controllers;

import fr.uga.miage.l3.endpoints.TourneeEndpoints;
import fr.uga.miage.l3.request.TourneeRequest;
import fr.uga.miage.l3.responses.TourneeResponseDTO;
import fr.uga.miage.l3.services.TourneeService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.Set;

@RequiredArgsConstructor
@Controller
public class TourneeController  implements TourneeEndpoints {
    private final TourneeService tourneeService;

    @Override
    public TourneeResponseDTO createTournee(@RequestBody TourneeRequest tourneeRequest) {
        /*
private int idTournee;
    private double tempsTotal;
    private Date dateTournee;
    private Timestamp heureDepart;
    private double distanceTotale;
    private String statutTournee;
    private Set<CommandResponseDTO> commandes;
    private int numeroEquipe;        * */
        System.out.println("idTournee="+tourneeRequest.getStatutTournee());
        System.out.println("tempsTotal="+tourneeRequest.getTempsTotal());
        System.out.println("dateTournee"+tourneeRequest.getDateTournee());
        System.out.println("heureDepart"+tourneeRequest.getHeureDepart());
        System.out.println("distance"+tourneeRequest.getDistanceTotale());

        return tourneeService.createTournee(tourneeRequest);
    }

    @Override
    public Set<TourneeResponseDTO> getAllTournees(LocalDate date) {
        return Set.of();
    }
}
