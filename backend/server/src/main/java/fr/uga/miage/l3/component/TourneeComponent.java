package fr.uga.miage.l3.component;

import fr.uga.miage.l3.models.EquipeEntity;
import fr.uga.miage.l3.models.TourneeEntity;
import fr.uga.miage.l3.repository.EquipeRepository;
import fr.uga.miage.l3.repository.TourneeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TourneeComponent {
   private final TourneeRepository tourneeRepository;
   private final EquipeRepository equipeRepository;
    public TourneeEntity createTournee(TourneeEntity tourneeEntity){
        return tourneeRepository.save(tourneeEntity);
    }
}
