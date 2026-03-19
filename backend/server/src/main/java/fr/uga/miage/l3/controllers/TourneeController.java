package fr.uga.miage.l3.controllers;

import fr.uga.miage.l3.endpoints.TourneeEndpoints;
import fr.uga.miage.l3.request.TourneeRequest;
import fr.uga.miage.l3.responses.TourneeResponseDTO;
import fr.uga.miage.l3.services.TourneeService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", methods = {
        RequestMethod.GET,
        RequestMethod.POST,
        RequestMethod.PUT,
        RequestMethod.DELETE
})

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/tournees")
public class TourneeController  implements TourneeEndpoints {
    private final TourneeService tourneeService;

    @Override
    public TourneeResponseDTO createTournee(@RequestBody TourneeRequest tourneeRequest) {

        return tourneeService.createTournee(tourneeRequest);
    }

    @Override
    public List<TourneeResponseDTO> getAllTournees(LocalDate date) {
        return tourneeService.getTourneesByDate(date);
    }
    @Override
    public TourneeResponseDTO updateTournee(@PathVariable int id, @RequestBody TourneeRequest tourneeRequest) {
        return tourneeService.updateTournee(id, tourneeRequest);
}
    @Override
    public void deleteTournee(@PathVariable(name = "id") int id) {
        tourneeService.deleteTournee(id);
    }
}
