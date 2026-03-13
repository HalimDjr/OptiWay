package fr.uga.miage.l3.component;

import fr.uga.miage.l3.enums.StatutCommande;
import fr.uga.miage.l3.exceptions.technical.NotFoundElementException;
import fr.uga.miage.l3.models.CommandeEntity;
import fr.uga.miage.l3.repository.CommandeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class CommandComponent {
    private final CommandeRepository commandeRepository;

    public Set<CommandeEntity> getAllCommandesNonlivres() {
        Set<StatutCommande> statuts = Set.of(StatutCommande.NON_PLANIFIEE, StatutCommande.ANNULEE);
        return commandeRepository.getNonPlanifieesOuAnnulees();
    }

    public CommandeEntity getCommandById( String commandId) throws NotFoundElementException {
        System.out.println("command_id= "+commandId);
        return commandeRepository.findById(commandId).orElseThrow(()->new NotFoundElementException(String.format("COMMANDE NON EXISTANTE")));
    }
}


