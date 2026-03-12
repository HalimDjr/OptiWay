package fr.uga.miage.l3.services;

import fr.uga.miage.l3.component.CommandComponent;
import fr.uga.miage.l3.mappers.CommandeMapper;
import fr.uga.miage.l3.models.CommandeEntity;
import fr.uga.miage.l3.responses.CommandResponseDTO;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class CommandeService {
   private final CommandComponent commandComponent;
    private final CommandeMapper commandMapper;




    public Set<CommandResponseDTO> getAllCommandesNonLivres(){
        Set<CommandeEntity> commandeEntities=commandComponent.getAllCommandesNonlivres();
        return commandMapper.toResponse(commandeEntities);
    }

}
