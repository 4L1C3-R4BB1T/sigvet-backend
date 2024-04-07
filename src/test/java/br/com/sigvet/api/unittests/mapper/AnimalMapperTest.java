package br.com.sigvet.api.unittests.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.sigvet.api.application.mapper.animal.AnimalMapper;
import br.com.sigvet.api.application.mapper.base.IAnimalMapper;
import br.com.sigvet.api.application.mapper.base.IClienteMapper;
import br.com.sigvet.api.core.domain.entities.Animal;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.domain.entities.Documento;
import br.com.sigvet.api.core.domain.entities.Endereco;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.infrastructure.entity.AnimalEntity;
import br.com.sigvet.api.infrastructure.entity.ClienteEntity;
import br.com.sigvet.api.infrastructure.entity.EnderecoEntity;
import br.com.sigvet.api.infrastructure.repository.ClienteJpaRepository;

@ExtendWith(MockitoExtension.class)
public class AnimalMapperTest {
    
    @Mock
    ClienteJpaRepository clienteJpaRepository;

    @Mock
    IClienteMapper clienteMapper;

    IAnimalMapper animalMapper;

    static AnimalEntity animalEntity;

    static Animal animal;

    static Cliente cliente;

    static ClienteEntity clienteEntity;

    @BeforeAll
    static void setupAll() throws DomainInvalidException {
        clienteEntity = ClienteEntity
            .builder()
            .id(1L)
            .senha("123")
            .nome("Gabriel")
            .cpf("173.645.097-20")
            .telefone("28999505410")
            .deleted(false)
            .animais(new ArrayList<>())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .email("gabrieltest@hotmail.com")
            .senha("joaquinho123")
            .usuario("gabiroba")
            .endereco(new EnderecoEntity())
            .build();

        animal = new Animal("Doguinho", "Peludo", LocalDate.parse("2000-04-10"));

        animalEntity = AnimalEntity
            .builder()
            .cliente(clienteEntity)
            .id(1L)
            .nome("Carlos")
            .raca("Cavalo")
            .dataNascimento(animal.getDataNascimento())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .build();
        

        clienteEntity.getAnimais().add(animalEntity);

        cliente = new Cliente( "gabiroba", "123", "gabrieltest@hotmail.com", "Gabriel", new Documento("173.645.097-20"), "28999505410", new Endereco());

        animal.setCliente(cliente);

    }

    @BeforeEach
    void setup() {
        animalMapper = new AnimalMapper(clienteJpaRepository, clienteMapper);
    }

    @Test
    void shouldMapperAnimalToEntity() {
        when(clienteMapper.fromDomainToEntity(any(Cliente.class))).thenReturn(clienteEntity);
        AnimalEntity animalEntity = animalMapper.fromDomainToEntity(animal);
        assertEquals(animalEntity.getNome(), animal.getNome(), () -> "Os nomes devem ser iguais");
        assertTrue(!animalEntity.getDeleted(), () -> "A propriedade deleted deve estar false");
    }

    @Test
    void shouldMapperEntityToAnimal() throws DomainInvalidException {
        when(clienteMapper.fromEntityToDomain(any(ClienteEntity.class))).thenReturn(cliente);
        Animal animal = animalMapper.fromEntityToDomain(animalEntity);
        assertNotNull(animal.getCreatedAt(), () -> "A propridade createdAt não deve ser null");
        assertNotNull(animal.getUpdatedAt(), () -> "A propridade updatedAt não deve ser null");
        assertNotNull(animal.getId(), () -> "A propridade id não deve ser null");
        assertNotNull(animal.getNome(), () -> "A propriedade nome não deve ser null");
        assertNotNull(animal.getDataNascimento(), () -> "A propriedade dataNascimento não deve ser null");
    }

    @Test
    void shouldThrowSomeExceptionsBecauseDomainInvalid() throws DomainInvalidException {
        String nome = "Cachorro".repeat(256);

        assertThrows(DomainInvalidException.class, () -> {
             new Animal(nome, "Ponei", LocalDate.parse("2024-04-07"));
        }, () -> "Deve lançar a exceção DomainInvalidException por causa do nome ultrapassando 255 caracteres");

        assertThrows(DomainInvalidException.class, () -> {
            new Animal(null, "Ponei", LocalDate.parse("2024-04-07"));
       }, () -> "Deve lançar a exceção DomainInvalidException por causa do nome nulo");
    }
}
