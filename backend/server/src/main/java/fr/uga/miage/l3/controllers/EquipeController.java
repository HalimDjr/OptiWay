package fr.uga.miage.l3.controllers;

import fr.uga.miage.l3.endpoints.EquipeEndpoints;
import fr.uga.miage.l3.services.EquipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

/// ////
@RestController
@RequiredArgsConstructor
public class EquipeController implements EquipeEndpoints {
    private final EquipeService equipeService;

    @Override
    public Long getNombreEquipes(){
        return equipeService.getNombreEquipes();
    }

    @Override
    public double getHeuresMax(){
        return equipeService.getHeuresMax();
    }

}
