package fr.uga.miage.l3.it.endpoints;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import static fr.uga.miage.l3.it.utils.TestUtils.*;

class EquipeEndpointsTest {

    Class<?> equipeEndpointsClass = Class.forName("fr.uga.miage.l3.endpoints.EquipeEndpoints");

    public EquipeEndpointsTest() throws ClassNotFoundException {}

    @Test
    void testAnnotationInClass() throws Exception {
        verifyClassHaveAnnotation(equipeEndpointsClass, RequestMapping.class);
        verifyClassHaveAnnotationField(equipeEndpointsClass, RequestMapping.class, "value", new String[]{"/api/equipes"});

        verifyClassHaveAnnotation(equipeEndpointsClass, Tag.class);
        verifyClassHaveAnnotationField(equipeEndpointsClass, Tag.class, "name", "Equipes");
        verifyClassHaveAnnotationField(equipeEndpointsClass, Tag.class, "description", "Gestion des équipes");
    }

    @Nested
    class Endpoint {

        @Test
        void getNombreEquipes() throws Exception {
            verifyMethodIsDeclared(equipeEndpointsClass, "getNombreEquipes");
            verifyMethodHaveAnnotation(equipeEndpointsClass, "getNombreEquipes", Operation.class);
            verifyMethodAnnotationField(
                    equipeEndpointsClass,
                    "getNombreEquipes",
                    Operation.class,
                    "description",
                    "Récupérer le nombre d'équipes"
            );

            verifyApiResponse(equipeEndpointsClass, "getNombreEquipes", 1);
            verifyApiResponseField(equipeEndpointsClass, "getNombreEquipes", 0, "responseCode", "200");
            verifyApiResponseField(equipeEndpointsClass, "getNombreEquipes", 0, "description", "succes");

            verifyMethodHaveAnnotation(equipeEndpointsClass, "getNombreEquipes", GetMapping.class);
            verifyMethodAnnotationField(equipeEndpointsClass, "getNombreEquipes", GetMapping.class, "value", new String[]{"/nb-equipes"});
        }

        @Test
        void getHeuresMax() throws Exception {
            verifyMethodIsDeclared(equipeEndpointsClass, "getHeuresMax");
            verifyMethodHaveAnnotation(equipeEndpointsClass, "getHeuresMax", Operation.class);
            verifyMethodAnnotationField(
                    equipeEndpointsClass,
                    "getHeuresMax",
                    Operation.class,
                    "description",
                    "Récupérer le nombre d'heures max"
            );

            verifyApiResponse(equipeEndpointsClass, "getHeuresMax", 1);
            verifyApiResponseField(equipeEndpointsClass, "getHeuresMax", 0, "responseCode", "200");
            verifyApiResponseField(equipeEndpointsClass, "getHeuresMax", 0, "description", "succes");

            verifyMethodHaveAnnotation(equipeEndpointsClass, "getHeuresMax", GetMapping.class);
            verifyMethodAnnotationField(equipeEndpointsClass, "getHeuresMax", GetMapping.class, "value", new String[]{"/heures-max"});
        }

        @Test
        void getAllEquipes() throws Exception {
            verifyMethodIsDeclared(equipeEndpointsClass, "getAllEquipes");
            verifyMethodHaveAnnotation(equipeEndpointsClass, "getAllEquipes", Operation.class);
            verifyMethodAnnotationField(
                    equipeEndpointsClass,
                    "getAllEquipes",
                    Operation.class,
                    "description",
                    "Récupérer toutes les équipes"
            );

            verifyMethodHaveAnnotation(equipeEndpointsClass, "getAllEquipes", GetMapping.class);
            verifyMethodAnnotationField(equipeEndpointsClass, "getAllEquipes", GetMapping.class, "value", new String[]{});
        }
    }
}