package fr.uga.miage.l3.services;

import fr.uga.miage.l3.component.ElementComponent;
import fr.uga.miage.l3.exceptions.rest.BadRequestRestException;
import fr.uga.miage.l3.exceptions.rest.NotFoundElementRestException;
import fr.uga.miage.l3.exceptions.technical.NotFoundElementException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ElementService {
    private final ElementComponent elementComponent;

    public String find(String name) {
        try {
            return elementComponent.find(name);
        } catch (NotFoundElementException e) {
            throw new NotFoundElementRestException(e.getMessage());
        }
    }

    public String create(String name) {
        if (name == null) throw new BadRequestRestException("Creation with null name is not possible");
        return elementComponent.create(name);
    }


}
