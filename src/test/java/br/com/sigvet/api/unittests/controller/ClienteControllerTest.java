package br.com.sigvet.api.unittests.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.sigvet.api.application.dto.CriarClienteDTO;
import br.com.sigvet.api.application.mapper.ClienteDTOMapper;
import br.com.sigvet.api.application.mapper.ClienteMapper;
import br.com.sigvet.api.application.usecaseImpl.cliente.AtualizarClienteUseCase;
import br.com.sigvet.api.application.usecaseImpl.cliente.CadastrarClienteUseCase;
import br.com.sigvet.api.application.usecaseImpl.cliente.DeletarClienteUseCase;
import br.com.sigvet.api.application.usecaseImpl.cliente.ListarClientesUseCase;
import br.com.sigvet.api.application.usecaseImpl.cliente.ObterClientePorIdUseCase;
import br.com.sigvet.api.core.domain.entities.Cidade;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.domain.entities.Documento;
import br.com.sigvet.api.core.domain.entities.Endereco;
import br.com.sigvet.api.core.domain.entities.UF;
import br.com.sigvet.api.core.exception.DomainInvalidException;

@WebMvcTest
@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {
    
    private static ObjectMapper objectMapper = new ObjectMapper();
    
    @Autowired
    private MockMvc mockMvc;

    private static Cliente cliente;

    private static CriarClienteDTO criarClienteDTO;

    @MockBean
    private CadastrarClienteUseCase cadastrarClienteUseCase;

    @MockBean
    private ClienteMapper clienteMapper;

    @MockBean
    private ClienteDTOMapper clienteDTOMapper;

    @MockBean
    private ObterClientePorIdUseCase obterClientePorIdUseCase;

    @MockBean
    private AtualizarClienteUseCase atualizarClienteUseCase;

    @MockBean
    private DeletarClienteUseCase deletarClienteUseCase;

    @MockBean
    private ListarClientesUseCase listarClientesUseCase;

    @BeforeAll
    public static void setup() throws DomainInvalidException {
        criarClienteDTO = new CriarClienteDTO(
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
          2L               
      );

      cliente = new Cliente(
        criarClienteDTO.usuario(),
        criarClienteDTO.senha(),
        criarClienteDTO.email(),
        criarClienteDTO.nome(),
        new Documento(criarClienteDTO.cpf()),
        criarClienteDTO.telefone(),
        new Endereco(criarClienteDTO.rua(), criarClienteDTO.bairro(), criarClienteDTO.cep(), criarClienteDTO.numero(), 
          new Cidade("Castelo", new UF("ES", "Espírito Santo"))
        )
      );

    }


    @DisplayName("Criar cliente com requisição post retorna cliente criado")
    @Test
    public void testeCriarClienteComRequisicaoPostRetornaCliente() throws JsonProcessingException, Exception {

        String expectedObject = """
            {
                "success": true,
                "status": 201,
                "message": "Cliente retornado"
            }
          """;
                

        when(cadastrarClienteUseCase.executar(any(Cliente.class))).then(answer -> {
            Cliente cliente = answer.getArgument(0);
            cliente.setId(11L);
            cliente.getEndereco().setId(11L);
            cliente.getEndereco().getCidade().setId(11L);
            return cliente;
        });

        when(clienteMapper.toCliente(any(CriarClienteDTO.class))).thenReturn(cliente);

        mockMvc.perform(
          MockMvcRequestBuilders.post("/api/clientes")
          .content(objectMapper.writeValueAsString(criarClienteDTO))
          .contentType(MediaType.APPLICATION_JSON)
        )
        .andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.content().json(expectedObject, true));
      }


}
