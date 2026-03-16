package fr.uga.miage.l3.services;

import fr.uga.miage.l3.component.CommandComponent;
import fr.uga.miage.l3.exceptions.rest.NotFoundCommandRestException;
import fr.uga.miage.l3.exceptions.technical.NotFoundCommandException;
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
        try {
            return commandEntitySetToCommandResponseSet(commandComponent.getAllCommandesNonlivres());
        }catch(NotFoundCommandException e){
            throw new NotFoundCommandRestException("not foundCommand REst Exception");
        }
    }

    public Integer getNombreCommandesNonLivres(){
        return getAllCommandesNonLivres().size() ;
    }

    /**
     * @Description:
     * récupérer un Set De commandEntitties non livrés
     * mapper chaque CommandEntity vers un CommandResponseDTO
     * assigner latitude longitude et le status de livraison manuellement
     *
     *
     * @param commandeEntitySet :un Set des Commandes Non Livrés
     * @return commandResponseDTOSet: un Set de CommandeResponseDTO Set mappé avec commandEntitySet
     * */
    private Set<CommandResponseDTO> commandEntitySetToCommandResponseSet(Set<CommandeEntity> commandeEntitySet){
        Set<CommandResponseDTO> commandResponseDTOSet = new HashSet<>();
        Iterator<CommandeEntity> iterator = commandeEntitySet.iterator();

        while(iterator.hasNext()){
            CommandeEntity commandEntityTemp=iterator.next();
            CommandResponseDTO commandResponseDTOTemp=commandMapper.toResponse(commandEntityTemp);

            commandResponseDTOTemp.setLongtitude(commandEntityTemp.getAdresse().getLongitude());
            commandResponseDTOTemp.setLatitude(commandEntityTemp.getAdresse().getLatitude());
            commandResponseDTOTemp.setStatus(commandEntityTemp.getStatut().toString());

            commandResponseDTOSet.add(commandResponseDTOTemp);

        }
        return commandResponseDTOSet;
    }





}
