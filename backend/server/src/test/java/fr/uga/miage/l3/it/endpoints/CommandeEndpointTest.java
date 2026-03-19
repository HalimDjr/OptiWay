package fr.uga.miage.l3.it.endpoints;

import io.swagger.v3.oas.annotations.Operation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import static fr.uga.miage.l3.it.utils.TestUtils.*;

class CommandEndpointsTest {

    Class<?> commandEndpointClass = Class.forName("fr.uga.miage.l3.endpoints.CommandEndpoints");

    public CommandEndpointsTest() throws ClassNotFoundException {}

    @Test
    void testAnnotationInClass() throws Exception {
        verifyClassHaveAnnotation(commandEndpointClass, RequestMapping.class);
        verifyClassHaveAnnotationField(commandEndpointClass, RequestMapping.class, "value", new String[]{"/api/commands"});
    }

    @Nested
    class Endpoint {

        @Test
        void getAllCommandesNonLivres() throws Exception {
            verifyMethodIsDeclared(commandEndpointClass, "getAllCommandesNonLivres");
            verifyMethodHaveAnnotation(commandEndpointClass, "getAllCommandesNonLivres", Operation.class);
            verifyMethodAnnotationField(
                    commandEndpointClass,
                    "getAllCommandesNonLivres",
                    Operation.class,
                    "description",
                    "récupérer toutes les commandes avec le status non livrés"
            );
            verifyApiResponse(commandEndpointClass, "getAllCommandesNonLivres", 1);
            verifyApiResponseField(commandEndpointClass, "getAllCommandesNonLivres", 0, "responseCode", "200");
            verifyApiResponseField(commandEndpointClass, "getAllCommandesNonLivres", 0, "description", "Les commandes sont récupéres ");
            verifyMethodHaveAnnotation(commandEndpointClass, "getAllCommandesNonLivres", ResponseStatus.class);
            verifyMethodAnnotationField(commandEndpointClass, "getAllCommandesNonLivres", ResponseStatus.class, "value", HttpStatus.OK);
            verifyMethodHaveAnnotation(commandEndpointClass, "getAllCommandesNonLivres", GetMapping.class);
            verifyMethodAnnotationField(commandEndpointClass, "getAllCommandesNonLivres", GetMapping.class, "value", new String[]{"/non-livres"});
        }

        @Test
        void getNombreCommandesNonLivres() throws Exception {
            verifyMethodIsDeclared(commandEndpointClass, "getNombreCommandesNonLivres");
            verifyMethodHaveAnnotation(commandEndpointClass, "getNombreCommandesNonLivres", Operation.class);
            verifyMethodAnnotationField(
                    commandEndpointClass,
                    "getNombreCommandesNonLivres",
                    Operation.class,
                    "description",
                    "le nombre de commandes non livrées"
            );
            verifyApiResponse(commandEndpointClass, "getNombreCommandesNonLivres", 1);
            verifyApiResponseField(commandEndpointClass, "getNombreCommandesNonLivres", 0, "responseCode", "200");
            verifyApiResponseField(commandEndpointClass, "getNombreCommandesNonLivres", 0, "description", "le nombre de comandes non livrées est trouvé ");
            verifyMethodHaveAnnotation(commandEndpointClass, "getNombreCommandesNonLivres", ResponseStatus.class);
            verifyMethodAnnotationField(commandEndpointClass, "getNombreCommandesNonLivres", ResponseStatus.class, "value", HttpStatus.OK);
            verifyMethodHaveAnnotation(commandEndpointClass, "getNombreCommandesNonLivres", GetMapping.class);
            verifyMethodAnnotationField(commandEndpointClass, "getNombreCommandesNonLivres", GetMapping.class, "value", new String[]{"/non-livres/count"});
        }
    }
}