package br.com.sigvet.api.mapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.sigvet.api.application.mapper.AnimalMapper;
import br.com.sigvet.api.application.mapper.CidadeMapper;
import br.com.sigvet.api.application.mapper.ClienteMapper;
import br.com.sigvet.api.application.mapper.EnderecoMapper;
import br.com.sigvet.api.core.domain.entities.Cidade;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.domain.entities.Documento;
import br.com.sigvet.api.core.domain.entities.Endereco;
import br.com.sigvet.api.core.domain.entities.UF;
import br.com.sigvet.api.core.exception.DomainInvalidException;

@ExtendWith(MockitoExtension.class)
public class TestMappers {

    static Documento documento;

    static UF uf;

    static Cidade cidade;

    static Endereco endereco;

    @InjectMocks
    ClienteMapper clienteMapper;

    @Mock
    AnimalMapper animalMapper;

    @Mock
    EnderecoMapper enderecoMapper;

    @Mock
    CidadeMapper cidadeMapper;


    @BeforeAll
    static void setup() throws DomainInvalidException {
        documento = new Documento("12345678900");
        uf = new UF("ES", "Espírito Santo");
        cidade = new Cidade("Castelo", uf);
       // endereco = new Endereco("Rua Exemplo", "Bairro Exemplo", "12345-678", 123, cidade);
    }

    @DisplayName("Dado um objeto Cliente, quando mapeado para ClienteEntity, então a entidade correta é retornada")
    @Test
    void givenClienteObject_whenMappedToClienteEntity_thenCorrectEntityReturned() throws DomainInvalidException {
        Cliente cliente = new Cliente("usuario", "senha", "cliente@example.com", "Nome do Cliente", documento, "123456789", endereco);


        //TODO Testar mappers
     

    }
    
}
