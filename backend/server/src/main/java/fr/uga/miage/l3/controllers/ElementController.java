package fr.uga.miage.l3.controllers;

import fr.uga.miage.l3.endpoints.ElementEndpoints;
import fr.uga.miage.l3.request.ElementRequest;
import fr.uga.miage.l3.services.ElementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ElementController implements ElementEndpoints {
    private final ElementService elementService;

    @Override
    public String create(ElementRequest request) {
        return elementService.create(request.name());
    }

    @Override
    public String element(String name) {
        return elementService.find(name);
    }
}
