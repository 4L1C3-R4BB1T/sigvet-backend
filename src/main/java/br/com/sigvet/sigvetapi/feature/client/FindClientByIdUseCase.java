package br.com.sigvet.sigvetapi.feature.client;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.FindByIdUseCase;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindClientByIdUseCase implements FindByIdUseCase<ClientEntity> {

    private final ClientRepository repository;

    @Override
    public ClientEntity execute(Long id) {
        return repository.findById(Objects.requireNonNull(id))
            .orElseThrow(() -> new ApplicationException("Client with %d not found".formatted(id)));
    }
}
