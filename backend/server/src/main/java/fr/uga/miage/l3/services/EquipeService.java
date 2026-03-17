package fr.uga.miage.l3.services;

import fr.uga.miage.l3.component.EquipeComponent;
import fr.uga.miage.l3.mappers.EquipeMapper;
import fr.uga.miage.l3.responses.EquipeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EquipeService {
    private final EquipeComponent equipeComponent;
    private final EquipeMapper equipeMapper;

    public Long getNombreEquipes(){
        return equipeComponent.getNombreEquipes();
    }

    public double getHeuresMax(){
        return equipeComponent.getHeuresMax();
    }

    public List<EquipeResponseDTO> getAllEquipes(){
        return equipeComponent.getAllEquipes().stream()
                .map(equipeMapper::toResponse)
                .collect(Collectors.toList());
    }
}