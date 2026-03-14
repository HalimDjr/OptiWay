package fr.uga.miage.l3.component;

import fr.uga.miage.l3.exceptions.rest.NotFoundEquipeResetException;
import fr.uga.miage.l3.exceptions.technical.NotFoundEquipeEntityException;
import fr.uga.miage.l3.models.EquipeEntity;
import fr.uga.miage.l3.repository.EquipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/// ////
@Component
@RequiredArgsConstructor
public class EquipeComponent {
    private final EquipeRepository equipeRepository;

    public EquipeEntity getEquipeEntity(int numeroEquipe) {
        return equipeRepository.findById(numeroEquipe).orElseThrow(()-> new NotFoundEquipeEntityException(String.format("l'quipe qui à pour id %s n'existe pas ",numeroEquipe)));
    }

    public Long getNombreEquipes(){
        return equipeRepository.count();
    }

    public double getHeuresMax(){
        EquipeEntity equipe = equipeRepository.findTopByOrderByNbHeuresMaxDesc();
        if(equipe == null){
            // si la table est vide
            throw new NotFoundEquipeResetException("Aucune équipe trouvée");
        }
        return equipe.getNbHeuresMax();
    }
}