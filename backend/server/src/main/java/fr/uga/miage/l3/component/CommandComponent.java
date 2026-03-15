package fr.uga.miage.l3.component;

import fr.uga.miage.l3.enums.StatutCommande;
import fr.uga.miage.l3.exceptions.technical.NotFoundCommandException;
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
        try {
            return commandeRepository.getNonPlanifieesOuAnnulees();
        }catch(Exception e){
            throw new NotFoundCommandException("erreur dans la récupération des Commandes non livrés");
        }
    }

    public int getNombreCommandesNonLivrees(){
        return commandeRepository.countByStatut(StatutCommande.NON_PLANIFIEE);
    }
}

