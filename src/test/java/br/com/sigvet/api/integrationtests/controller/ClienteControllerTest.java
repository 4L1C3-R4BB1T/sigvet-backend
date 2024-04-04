package br.com.sigvet.api.integrationtests.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import br.com.sigvet.api.application.dto.BaseResponse;
import br.com.sigvet.api.application.dto.ClienteDTO;
import br.com.sigvet.api.application.dto.CriarClienteDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ClienteControllerTest {
    
    @Autowired
    private TestRestTemplate testRestTemplate;

    @DisplayName("Criar cliente com requisição post retorna cliente criado")
    @Test
    public void testeCriarClienteComRequisicaoPostRetornaCliente() throws URISyntaxException {
        CriarClienteDTO criarClienteDTO = new CriarClienteDTO(
          "alice_smith",    
          "Alice Smith",    
          "password456",     
          "alice@example.com", 
          "987.654.321-00",   
          "1122334455",      
          "Avenida Central",     
          "Vila Nova",           
          "54321-987",         
          456,             
          1L               
      );

        RequestEntity<CriarClienteDTO> requestEntity = new RequestEntity<>(criarClienteDTO, HttpMethod.POST, new URI("/api/clientes"));
        ResponseEntity<BaseResponse<ClienteDTO>> response = testRestTemplate.exchange(requestEntity, ParameterizedTypeReference.forType(BaseResponse.class));
        assertTrue(response.getStatusCode().isSameCodeAs(HttpStatus.CREATED), () -> "O Status de retorno deve ser o 201 - CREATED");
        //TODO fazer mais testes
    }
    

    @DisplayName("Deletar cliente com requisição delete retorna booleano")
    @Test
    public void testeDeletarClienteComRequisicaoDeleteRetornaBoolean() throws URISyntaxException {
        Long clienteId = 1L;

        RequestEntity<Void> requestEntity = new RequestEntity<>(HttpMethod.DELETE, new URI("/api/clientes/%d".formatted(clienteId)));
        ResponseEntity<BaseResponse<Boolean>> response = testRestTemplate.exchange(requestEntity, ParameterizedTypeReference.forType(BaseResponse.class));
        assertTrue(response.getBody().getResult(), () -> "O resultado precisa ser true");
    }



}
