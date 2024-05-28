package br.com.sigvet.sigvetapi.feature.client;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Disabled;

@DataJpaTest
public class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Disabled
    @Test
    void givenValidClientIdAndNotMarkedAsDeleted_whenCallFindById_thenShouldReturnClient() {
        final long expectedId = 1L;
        final boolean expectedDeleted = false;
        final var result = clientRepository.findByIdAndNotMarkedAsDeleted(expectedId);
        assertTrue(result.isPresent());

        result.ifPresent(clientEntity -> {
            assertNotNull(clientEntity.getId());
            assertNotNull(clientEntity.getUsername());
            assertNotNull(clientEntity.getPassword());
            assertNotNull(clientEntity.getEmail());
            assertNotNull(clientEntity.getName());
            assertNotNull(clientEntity.getDocument());
            assertNotNull(clientEntity.getPhone());
            assertNotNull(clientEntity.getCreatedAt());
            assertNotNull(clientEntity.getUpdatedAt());
            assertEquals(expectedDeleted, clientEntity.isDeleted());
            assertNotNull(clientEntity.getAddress());
        });
    }

    @Disabled
    @Test
    void givenInvalidClientIdAndNotMarkedAsDeleted_whenCallFindById_thenShouldReturnNothing() {
        final long expectedId = 100L;
        final var result = clientRepository.findByIdAndNotMarkedAsDeleted(expectedId);
        assertTrue(result.isEmpty());
    }

    @Disabled
    @Test
    void givenADeletedClient_whenCallFindById_thenShouldReturnNothing() {
        final long expectedId = 1L;
        clientRepository.deleteById(expectedId);
        final var result = clientRepository.findByIdAndNotMarkedAsDeleted(expectedId);
        assertTrue(result.isEmpty());
    }

}
