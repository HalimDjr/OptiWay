package fr.uga.miage.l3.component;

import fr.uga.miage.l3.exceptions.technical.NotFoundElementException;
import fr.uga.miage.l3.models.ElementEntity;
import fr.uga.miage.l3.repository.ElementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ElementComponent {
    private final ElementRepository elementRepository;

    public String create(String name){
        return elementRepository.save(ElementEntity.builder().name(name).build()).getName();
    }

    public String find(String name) throws NotFoundElementException {
        return elementRepository
                .findByName(name)
                .orElseThrow(() -> new NotFoundElementException(String.format("element with name [%s] not found",name)))
                .getName();
    }
}
