package br.com.sigvet.api.integrationtests.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.sigvet.api.application.dto.animal.AnimalResponseDTO;
import br.com.sigvet.api.application.model.BaseResponse;
import br.com.sigvet.api.application.model.PageModel;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AnimalControllerTest {

    @LocalServerPort
    int port;
    
    @Autowired
    TestRestTemplate restTemplate;

    @DisplayName("Should create an animal when an HTTP POST request is made")
    @Test
    void shouldCreateAnimalWhenHttpPost() {
        String json = """
            {
                "nome": "Doguinho",
                "raca": "Alemão Aquático",
                "dataNascimento": "2000-04-10",
                "clienteId": 1
            }
        """;
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:{port}/api/animal/create").buildAndExpand(port).toUri();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        RequestEntity<String> request = new RequestEntity<>(json, headers, HttpMethod.POST, uri);
        ResponseEntity<BaseResponse<AnimalResponseDTO>> response = restTemplate.exchange(request, new ParameterizedTypeReference<BaseResponse<AnimalResponseDTO>>() {});
        assertNotNull(response.getBody(), () -> "A resposta de requisição não deve ser nula");
        assertEquals(HttpStatusCode.valueOf(201), response.getStatusCode(), () -> "O status code deve ser o 201");
    }

    @DisplayName("Should retrieve an animal when specifying filters")
    @Test
    void shouldRetriveAnimalWhenSpecifyingFilters() {
        String expectedNome = "Tobby";
        String expectedRaca = "Vira-lata";
        String equalFilters = "nome:=%s;raca:=%s".formatted(expectedNome, expectedRaca);

        URI uri = UriComponentsBuilder.fromUriString("http://localhost:{port}/api/animal/getAll")
            .queryParam("equal_filters", equalFilters)
            .buildAndExpand(port)
            .toUri();

        RequestEntity<Void> request = new RequestEntity<>(HttpMethod.GET, uri);
        ResponseEntity<PageModel<AnimalResponseDTO>> response = restTemplate.exchange(request, new ParameterizedTypeReference<>(){});
        PageModel<AnimalResponseDTO> pageModel = response.getBody();
        assertTrue(response.getStatusCode().is2xxSuccessful());
        assertTrue(pageModel.getElements().size() == 1, () -> "O total de elementos tem que ser 1");
    }

    @DisplayName("Should update animal when http put")
    @Test
    void shouldUpdateAnimalWhenHttpPut() {
        Long animalId = 2L;
        String json = """
            {
                "nome": "Doguinho 2",
                "raca": "Macaco Prego",
                "dataNascimento": "2000-04-10"
            }
        """;

        RequestEntity<String> request = RequestEntity
                                            .put("http://localhost:{port}/api/animal/update/{id}", port, animalId)
                                            .contentType(MediaType.APPLICATION_JSON)
                                            .body(json);

        ResponseEntity<BaseResponse<AnimalResponseDTO>> response = restTemplate.exchange(request, new ParameterizedTypeReference<>() {});
        BaseResponse<AnimalResponseDTO> baseResponse = response.getBody();
        AnimalResponseDTO responseAnimalDTO = baseResponse.getResult();
        assertEquals(HttpStatus.OK.value(), baseResponse.getStatus(), () -> "O status code deve ser o 200");
        assertEquals("Doguinho 2", responseAnimalDTO.nome(), () -> "O nome deve ser doguinho 2");
        assertEquals("Macaco Prego", responseAnimalDTO.raca(), () -> "A raca deve ser Macaco prego");
    }

    @DisplayName("Should successfully delete an animal upon receiving an HTTP DELETE request")
    @Test
    void shouldDeleteAnimalWhentHttpDelete() {
        Long animalId = 3L;
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:{port}/api/animal/delete/{id}").buildAndExpand(port, animalId).toUri();
        RequestEntity<Void> request = new RequestEntity<>(HttpMethod.DELETE, uri);
        ResponseEntity<BaseResponse<Boolean>> response = restTemplate.exchange(request, new ParameterizedTypeReference<>() {});
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode(), () -> "O status code deve ser 200");
        assertTrue(response.getBody().getResult(), () -> "O resultado deve ser true");
    }

    @DisplayName("Should not delete an animal upon receiving an HTTP DELETE request")
    @Test
    void shouldNotDeleteAnimalWhenHttpDelete() {
        Long animalId = 100L;
        URI uri = UriComponentsBuilder.fromUriString("http://localhost:{port}/api/animal/delete/{id}").buildAndExpand(port, animalId).toUri();
        RequestEntity<Void> request = new RequestEntity<>(HttpMethod.DELETE, uri);
        ResponseEntity<BaseResponse<Boolean>> response = restTemplate.exchange(request, new ParameterizedTypeReference<>() {});
        assertEquals(HttpStatusCode.valueOf(200), response.getStatusCode(), () -> "O status code deve ser 200");
        assertTrue(!response.getBody().getResult(), () -> "O resultado não deve ser true");
    }

}
