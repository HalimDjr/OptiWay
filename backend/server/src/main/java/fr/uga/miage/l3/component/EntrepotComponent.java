package fr.uga.miage.l3.component;

import fr.uga.miage.l3.exceptions.rest.NotFoundEntrepotRestException;
import fr.uga.miage.l3.models.EntrepotEntity;
import fr.uga.miage.l3.repository.EntrepotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EntrepotComponent {
    private final EntrepotRepository entrepotRepository;
    public EntrepotEntity getEntrepotById(int id){
        return entrepotRepository.findById(id)
                .orElseThrow(() -> new NotFoundEntrepotRestException("Entrepot not found"));
    }
}
