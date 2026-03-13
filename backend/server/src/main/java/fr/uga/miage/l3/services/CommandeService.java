package fr.uga.miage.l3.services;

import fr.uga.miage.l3.component.CommandComponent;
import fr.uga.miage.l3.exceptions.rest.NotFoundElementRestException;
import fr.uga.miage.l3.exceptions.technical.NotFoundElementException;
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
        Set<CommandeEntity> commandeEntities=commandComponent.getAllCommandesNonlivres();
        Set<CommandResponseDTO> commandResponseDTOSet= new HashSet<>();
        Iterator<CommandeEntity> iterator=commandeEntities.iterator();
        while(iterator.hasNext()){
            CommandeEntity commandEntityTemp= iterator.next();
            CommandResponseDTO commandDTOTemp=commandMapper.toResponse(commandEntityTemp);
            commandDTOTemp.setStatus(commandEntityTemp.getStatut().toString());
            commandDTOTemp.setLatitude(commandEntityTemp.getAdresse().getLatitude());
            commandDTOTemp.setLongtitude(commandEntityTemp.getAdresse().getLongitude());


            System.out.println("command_id_dto= "+commandDTOTemp.getNumeroCommande());
            System.out.println("numeroCommande = " + commandDTOTemp.getNumeroCommande());
            System.out.println("dateLimite      = " + commandDTOTemp.getDateLimite());
            System.out.println("status          = " + commandDTOTemp.getStatus());
            System.out.println("poids           = " + commandDTOTemp.getPoids());
            System.out.println("volume          = " + commandDTOTemp.getVolume());
            commandResponseDTOSet.add(commandDTOTemp);



        }
        return commandResponseDTOSet;
    }

    public CommandResponseDTO getCommandeById(String commande_id){
        try {
            System.out.println("command_id= "+commande_id);
            CommandeEntity commandeEntityResponse=commandComponent.getCommandById(commande_id);
            System.out.println("commande_entitytt_response ="+commandeEntityResponse.getNumeroCommande());
            CommandResponseDTO commandResponseDTO=commandMapper.toResponse( commandeEntityResponse);
            commandResponseDTO.setStatus(commandeEntityResponse.getStatut().toString());
            System.out.println("command_id_dto= "+commandResponseDTO.getNumeroCommande());
            System.out.println("numeroCommande = " + commandResponseDTO.getNumeroCommande());
            System.out.println("dateLimite      = " + commandResponseDTO.getDateLimite());
            System.out.println("status          = " + commandResponseDTO.getStatus());
            System.out.println("poids           = " + commandResponseDTO.getPoids());
            System.out.println("volume          = " + commandResponseDTO.getVolume());
            return commandResponseDTO;
        }catch(NotFoundElementException e){
            throw new NotFoundElementRestException(e.getMessage());
        }
    }

}
