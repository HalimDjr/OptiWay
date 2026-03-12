package fr.uga.miage.l3.component;

import fr.uga.miage.l3.models.EquipeEntity;
import fr.uga.miage.l3.repository.EquipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/// ////
@Component
@RequiredArgsConstructor
public class EquipeComponent {
    private final EquipeRepository equipeRepository;

    public Long getNombreEquipes(){
        return equipeRepository.count();
    }

    public double getHeuresMax(){
        EquipeEntity equipe = equipeRepository.findTopByOrderByNbHeuresMaxDesc();
        if(equipe == null) return 0; // si la table est vide
        return equipe.getNbHeuresMax();
    }
}
