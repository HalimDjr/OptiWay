package fr.uga.miage.l3.services;

import fr.uga.miage.l3.component.CommandComponent;
import fr.uga.miage.l3.mappers.CommandeMapper;
import fr.uga.miage.l3.models.CommandeEntity;
import fr.uga.miage.l3.responses.CommandResponseDTO;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.spi.CommandAcceptanceException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CommandeService {
   private final CommandComponent commandComponent;
   private final CommandeMapper commandMapper;


    public Set<CommandResponseDTO> getAllCommandesNonLivres(){

        Set<CommandeEntity> commandeEntities = commandComponent.getAllCommandesNonlivres();

        Set<CommandResponseDTO> commandResponseDTOSet = new HashSet<>();

        Iterator<CommandeEntity> iterator = commandeEntities.iterator();

        while(iterator.hasNext()){
            commandResponseDTOSet.add(commandMapper.toResponse(iterator.next()));
        }

        return commandResponseDTOSet;
    }

    public Integer getNombreCommandesNonLivres(){

        return commandComponent.getNombreCommandesNonLivrees() ;
    }

}
