package fr.uga.miage.l3.services;

import fr.uga.miage.l3.component.EntrepotComponent;
import fr.uga.miage.l3.exceptions.rest.NotFoundEntrepotRestException;
import fr.uga.miage.l3.exceptions.technical.NotFoundEntrepotException;
import fr.uga.miage.l3.mappers.EntrepotMapper;
import fr.uga.miage.l3.models.EntrepotEntity;
import fr.uga.miage.l3.responses.EntrepotResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EntrepotService {
    private final EntrepotComponent entrepotComponent;
    private final EntrepotMapper entrepotMapper;
    public EntrepotResponseDTO getEntrepotById(int id){
        System.out.println("Récupération de l'entrepot selon son id");
        EntrepotEntity entrepot = entrepotComponent.getEntrepotById(id);
        return entrepotMapper.toResponse(entrepot);
    }
}
