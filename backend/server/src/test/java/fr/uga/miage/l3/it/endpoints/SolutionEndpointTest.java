package fr.uga.miage.l3.it.endpoints;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static fr.uga.miage.l3.it.utils.TestUtils.*;

class SolutionEndpointsTest {

    Class<?> solutionEndpointsClass = Class.forName("fr.uga.miage.l3.endpoints.SolutionEndpoints");

    public SolutionEndpointsTest() throws ClassNotFoundException {}

    @Nested
    class Endpoint {

        @Test
        void saveSolution() throws Exception {
            verifyMethodIsDeclared(solutionEndpointsClass, "saveSolution");
            verifyMethodHaveAnnotation(solutionEndpointsClass, "saveSolution", PostMapping.class);
            verifyMethodAnnotationField(solutionEndpointsClass, "saveSolution", PostMapping.class, "value", new String[]{"/solution"});

            verifyMethodHaveParam(solutionEndpointsClass, "saveSolution", "request");
            verifyMethodParamHaveAnnotation(solutionEndpointsClass, "saveSolution", "request", RequestBody.class);
        }

        @Test
        void getAllSolutions() throws Exception {
            verifyMethodIsDeclared(solutionEndpointsClass, "getAllSolutions");
            verifyMethodHaveAnnotation(solutionEndpointsClass, "getAllSolutions", GetMapping.class);
            verifyMethodAnnotationField(solutionEndpointsClass, "getAllSolutions", GetMapping.class, "value", new String[]{});
        }

        @Test
        void activerSolution() throws Exception {
            verifyMethodIsDeclared(solutionEndpointsClass, "activerSolution");
            verifyMethodHaveAnnotation(solutionEndpointsClass, "activerSolution", PutMapping.class);
            verifyMethodAnnotationField(solutionEndpointsClass, "activerSolution", PutMapping.class, "value", new String[]{"/{id}/activer"});

            verifyMethodHaveParam(solutionEndpointsClass, "activerSolution", "id");
            verifyMethodParamHaveAnnotation(solutionEndpointsClass, "activerSolution", "id", PathVariable.class);
        }

        @Test
        void getSolutionById() throws Exception {
            verifyMethodIsDeclared(solutionEndpointsClass, "getSolutionById");
            verifyMethodHaveAnnotation(solutionEndpointsClass, "getSolutionById", GetMapping.class);
            verifyMethodAnnotationField(solutionEndpointsClass, "getSolutionById", GetMapping.class, "value", new String[]{"/{id}"});

            verifyMethodHaveParam(solutionEndpointsClass, "getSolutionById", "id");
            verifyMethodParamHaveAnnotation(solutionEndpointsClass, "getSolutionById", "id", PathVariable.class);
        }
    }
}