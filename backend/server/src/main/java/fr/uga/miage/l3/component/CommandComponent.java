package fr.uga.miage.l3.component;

import fr.uga.miage.l3.enums.StatutCommande;
import fr.uga.miage.l3.models.CommandeEntity;
import fr.uga.miage.l3.repository.CommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class CommandComponent {
    private final CommandeRepository commandeRepository;

    public Set<CommandeEntity> getAllCommandesNonlivres() {
        return commandeRepository.getNonPlanifieesOuAnnulees();
    }

    public int getNombreCommandesNonLivrees(){
        return commandeRepository.countByStatut(StatutCommande.NON_PLANIFIEE);
    }
}

