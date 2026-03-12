package fr.uga.miage.l3.it;

import fr.uga.miage.l3.request.ElementRequest;
import fr.uga.miage.l3.errors.ErrorDTO;
import fr.uga.miage.l3.models.ElementEntity;
import fr.uga.miage.l3.repository.ElementRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@AutoConfigureWebTestClient
class HelloWorldControllerTestIT {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private ElementRepository elementRepository;

    @BeforeEach
    void setup(){
        elementRepository.deleteAll();
    }
    
    
    
    @Test
    @DisplayName("POST-201 element created")
    void createElement() {
        // when
        webTestClient
                .post()
                .uri("/api/element")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new ElementRequest("test"))
                .exchange()
                .expectStatus()
                .isCreated()
                .expectBody(String.class)
                .value(s->{
                    //then
                    assertThat(s).isEqualTo("test");
                    assertThat(elementRepository.count()).isOne();
                });
    }

    @Test
    @DisplayName("POST-400 element not created because name is null")
    void createElementWithNullName() {
        ErrorDTO expectedError = new ErrorDTO(
                "/api/element",
                "Creation with null name is not possible"
        );
        // when
        webTestClient
                .post()
                .uri("/api/element")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(new ElementRequest(null))
                .exchange()
                .expectStatus()
                .isBadRequest()
                .expectBody(ErrorDTO.class)
                .value(s -> {
                    assertThat(s).isEqualTo(expectedError);
                });
    }



    @Test
    @DisplayName("GET-200 element found")
    void getElement(){
        ElementEntity test = ElementEntity.builder().name("test").build();
        elementRepository.save(test);

        webTestClient
                .get()
                .uri("/api/element?name={test}","test")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(String.class)
                .value(s -> assertThat(s).isEqualTo("test"));
    }


    @Test
    @DisplayName("GET-404 element not found")
    void getElementNotFound(){
        ErrorDTO expectedError = new ErrorDTO(
                "/api/element",
                "element with name [test] not found"
        );


        webTestClient
                .get()
                .uri("/api/element?name={test}","test")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isNotFound()
                .expectBody(ErrorDTO.class)
                .value(errorDTO->{
                    assertThat(errorDTO).isEqualTo(expectedError);
                });
    }

}
