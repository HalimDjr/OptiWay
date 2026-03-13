package fr.uga.miage.l3.services;

import fr.uga.miage.l3.component.EquipeComponent;
import fr.uga.miage.l3.component.TourneeComponent;
import fr.uga.miage.l3.exceptions.technical.NotFoundEquipeEntityException;
import fr.uga.miage.l3.mappers.TourneeMapper;
import fr.uga.miage.l3.models.EquipeEntity;
import fr.uga.miage.l3.models.TourneeEntity;
import fr.uga.miage.l3.repository.EquipeRepository;
import fr.uga.miage.l3.request.TourneeRequest;
import fr.uga.miage.l3.responses.TourneeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TourneeService {
    private final TourneeComponent tourneeComponent;
    private final TourneeMapper tourneeMapper;
    private final EquipeComponent equipeComponent;
    private final EquipeRepository equipeRepository;

   public  TourneeResponseDTO createTournee(TourneeRequest tourneeRequest){
        TourneeEntity tourneeAjoute= tourneeMapper.toEntity(tourneeRequest);
        // recherche manuelle de l'quipe qui contient l'attribut envoyé , si l'équpe n'existe pas retourner un erreur
        //EquipeEntity equipeTournee=equipeRepository.findById(tourneeRequest.getNumeroEquipe()).orElseThrow(()-> new NotFoundEquipeEntityException(String.format("l'equipe qui contient l'id : %s n'existe pas , ajoutez là ou asigner manuellement une autre equipe ",tourneeRequest.getNumeroEquipe())));
        //tourneeAjoute.setEquipe(equipeTournee);
        return tourneeMapper.toResponse( tourneeComponent.createTournee(tourneeAjoute));

    }
}
