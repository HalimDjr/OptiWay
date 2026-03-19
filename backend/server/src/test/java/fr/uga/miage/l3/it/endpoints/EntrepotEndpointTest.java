package fr.uga.miage.l3.it.endpoints;

import io.swagger.v3.oas.annotations.Operation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import static fr.uga.miage.l3.it.utils.TestUtils.*;

class EntrepotEndpointTest {

    Class<?> entrepotEndpointClass = Class.forName("fr.uga.miage.l3.endpoints.EntrepotEndpoint");

    public EntrepotEndpointTest() throws ClassNotFoundException {}

    @Test
    void testAnnotationInClass() throws Exception {
        verifyClassHaveAnnotation(entrepotEndpointClass, RequestMapping.class);
        verifyClassHaveAnnotationField(entrepotEndpointClass, RequestMapping.class, "value", new String[]{"/api/entrepot"});
    }

    @Nested
    class Endpoint {

        @Test
        void getEntrepotById() throws Exception {
            verifyMethodIsDeclared(entrepotEndpointClass, "getEntrepotById");
            verifyMethodHaveAnnotation(entrepotEndpointClass, "getEntrepotById", Operation.class);
            verifyMethodAnnotationField(
                    entrepotEndpointClass,
                    "getEntrepotById",
                    Operation.class,
                    "description",
                    "récupérer un entrepot selon son id"
            );

            verifyApiResponse(entrepotEndpointClass, "getEntrepotById", 2);
            verifyApiResponseField(entrepotEndpointClass, "getEntrepotById", 0, "responseCode", "200");
            verifyApiResponseField(entrepotEndpointClass, "getEntrepotById", 0, "description", "entrepot récupéré avec succès");
            verifyApiResponseField(entrepotEndpointClass, "getEntrepotById", 1, "responseCode", "404");
            verifyApiResponseField(entrepotEndpointClass, "getEntrepotById", 1, "description", "id n'existe pas");

            verifyMethodHaveAnnotation(entrepotEndpointClass, "getEntrepotById", ResponseStatus.class);
            verifyMethodAnnotationField(entrepotEndpointClass, "getEntrepotById", ResponseStatus.class, "value", HttpStatus.OK);

            verifyMethodHaveAnnotation(entrepotEndpointClass, "getEntrepotById", GetMapping.class);
            verifyMethodAnnotationField(entrepotEndpointClass, "getEntrepotById", GetMapping.class, "value", new String[]{"/{id}"});

            verifyMethodHaveParam(entrepotEndpointClass, "getEntrepotById", "id");
            verifyMethodParamHaveAnnotation(entrepotEndpointClass, "getEntrepotById", "id", PathVariable.class);
            verifyMethodParamAnnotationField(entrepotEndpointClass, "getEntrepotById", "id", PathVariable.class, "name", "id");
        }
    }
}
