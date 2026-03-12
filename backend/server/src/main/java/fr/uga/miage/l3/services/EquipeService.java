package fr.uga.miage.l3.services;

import fr.uga.miage.l3.component.EquipeComponent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/// ///
@Service
@RequiredArgsConstructor
public class EquipeService {
    private final EquipeComponent equipeComponent;

    public Long getNombreEquipes(){
        return equipeComponent.getNombreEquipes();
    }

    public double getHeuresMax(){
        return equipeComponent.getHeuresMax();
    }
}
