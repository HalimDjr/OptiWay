package fr.uga.miage.l3.endpoints;

import fr.uga.miage.l3.request.SolutionRequest;
import fr.uga.miage.l3.responses.SolutionResponseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/api/solutions")
public interface SolutionEndpoints {
    @PostMapping("/solution")
    SolutionResponseDTO saveSolution(@RequestBody SolutionRequest request);

    @GetMapping
    List<SolutionResponseDTO> getAllSolutions();

    @PutMapping("/{id}/activer")
    SolutionResponseDTO activerSolution(@PathVariable int id);

    @GetMapping("/{id}")
    SolutionResponseDTO getSolutionById(@PathVariable int id);

    @PutMapping("/{id}")
    SolutionResponseDTO updateSolution(@PathVariable int id, @RequestBody SolutionRequest request);
    
    @PutMapping("/{id}/planifier")
    void planifierCommandes(@PathVariable int id);
}
