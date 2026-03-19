package fr.uga.miage.l3.controllers;

import fr.uga.miage.l3.endpoints.SolutionEndpoints;
import fr.uga.miage.l3.request.SolutionRequest;
import fr.uga.miage.l3.responses.SolutionResponseDTO;
import fr.uga.miage.l3.services.SolutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/solutions")
public class SolutionController implements SolutionEndpoints {
    private final SolutionService solutionService;

    @Override
    public SolutionResponseDTO saveSolution(@RequestBody SolutionRequest request) {
        return solutionService.saveSolution(request);
    }

    @Override
    public List<SolutionResponseDTO> getAllSolutions() {
        return solutionService.getAllSolutions();
    }

    @Override
    public SolutionResponseDTO activerSolution(@PathVariable int id) {
        return solutionService.activerSolution(id);
    }

    @Override
    public SolutionResponseDTO getSolutionById(@PathVariable int id) {
        return solutionService.getSolutionById(id);
    }

    @Override
    public SolutionResponseDTO updateSolution(@PathVariable int id, @RequestBody SolutionRequest request) {
        return solutionService.updateSolution(id, request);
}
}
