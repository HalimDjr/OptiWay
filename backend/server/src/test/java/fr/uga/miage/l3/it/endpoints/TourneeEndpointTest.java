package fr.uga.miage.l3.it.endpoints;

import io.swagger.v3.oas.annotations.Operation;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import static fr.uga.miage.l3.it.utils.TestUtils.*;

class TourneeEndpointsTest {

    Class<?> tourneeEndpointsClass = Class.forName("fr.uga.miage.l3.endpoints.TourneeEndpoints");

    public TourneeEndpointsTest() throws ClassNotFoundException {}

    @Test
    void testAnnotationInClass() throws Exception {
        verifyClassHaveAnnotation(tourneeEndpointsClass, RequestMapping.class);
        verifyClassHaveAnnotationField(tourneeEndpointsClass, RequestMapping.class, "value", new String[]{"/api/tournees"});
    }

    @Nested
    class Endpoint {

        @Test
        void createTournee() throws Exception {
            verifyMethodIsDeclared(tourneeEndpointsClass, "createTournee");

            verifyMethodHaveAnnotation(tourneeEndpointsClass, "createTournee", Operation.class);
            verifyMethodAnnotationField(
                    tourneeEndpointsClass,
                    "createTournee",
                    Operation.class,
                    "description",
                    "création d'une tournee"
            );

            verifyApiResponse(tourneeEndpointsClass, "createTournee", 1);
            verifyApiResponseField(tourneeEndpointsClass, "createTournee", 0, "responseCode", "200");
            verifyApiResponseField(tourneeEndpointsClass, "createTournee", 0, "description", "créer une playlist");

            verifyMethodHaveAnnotation(tourneeEndpointsClass, "createTournee", ResponseStatus.class);
            verifyMethodAnnotationField(tourneeEndpointsClass, "createTournee", ResponseStatus.class, "value", HttpStatus.OK);

            verifyMethodHaveAnnotation(tourneeEndpointsClass, "createTournee", PostMapping.class);
            verifyMethodAnnotationField(tourneeEndpointsClass, "createTournee", PostMapping.class, "value", new String[]{"/tournee"});

            verifyMethodHaveParam(tourneeEndpointsClass, "createTournee", "tourneeRequest");
            verifyMethodParamHaveAnnotation(tourneeEndpointsClass, "createTournee", "tourneeRequest", RequestBody.class);
        }

        @Test
        void getAllTournees() throws Exception {
            verifyMethodIsDeclared(tourneeEndpointsClass, "getAllTournees");

            verifyMethodHaveAnnotation(tourneeEndpointsClass, "getAllTournees", Operation.class);
            verifyMethodAnnotationField(
                    tourneeEndpointsClass,
                    "getAllTournees",
                    Operation.class,
                    "description",
                    "lister toutes les tournees d'une date donnée"
            );

            verifyApiResponse(tourneeEndpointsClass, "getAllTournees", 1);
            verifyApiResponseField(tourneeEndpointsClass, "getAllTournees", 0, "responseCode", "200");
            verifyApiResponseField(tourneeEndpointsClass, "getAllTournees", 0, "description", "récupérer toutes les tournes d'une data");

            verifyMethodHaveAnnotation(tourneeEndpointsClass, "getAllTournees", ResponseStatus.class);
            verifyMethodAnnotationField(tourneeEndpointsClass, "getAllTournees", ResponseStatus.class, "value", HttpStatus.OK);

            verifyMethodHaveAnnotation(tourneeEndpointsClass, "getAllTournees", GetMapping.class);
            verifyMethodAnnotationField(tourneeEndpointsClass, "getAllTournees", GetMapping.class, "value", new String[]{"/{date}"});

            verifyMethodHaveParam(tourneeEndpointsClass, "getAllTournees", "date");
            verifyMethodParamHaveAnnotation(tourneeEndpointsClass, "getAllTournees", "date", PathVariable.class);
            verifyMethodParamAnnotationField(tourneeEndpointsClass, "getAllTournees", "date", PathVariable.class, "name", "date");

            verifyMethodParamHaveAnnotation(tourneeEndpointsClass, "getAllTournees", "date", DateTimeFormat.class);
        }
    }
}