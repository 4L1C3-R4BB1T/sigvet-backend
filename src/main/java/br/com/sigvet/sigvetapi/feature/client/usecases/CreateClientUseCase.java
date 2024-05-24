package br.com.sigvet.sigvetapi.feature.client.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import br.com.sigvet.sigvetapi.common.usecases.CreateUseCase;
import br.com.sigvet.sigvetapi.feature.client.ClientRepository;
import br.com.sigvet.sigvetapi.feature.user.usecases.UserValidateUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CreateClientUseCase implements CreateUseCase<ClientEntity> {

    private final ClientRepository repository;

    private final UserValidateUseCase userValidateUseCase;

    @Transactional
    @Override
    public ClientEntity execute(ClientEntity source) {
        Objects.requireNonNull(source);

        final var errors = userValidateUseCase.execute(source);

        if (!errors.isEmpty()) {
            throw new ApplicationException("Client invalid", errors);
        }
        
        return repository.save(source);
    }

}
