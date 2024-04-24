package br.com.sigvet.sigvetapi.feature.client.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.UserValidateUseCase;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import br.com.sigvet.sigvetapi.common.repositories.CityRepository;
import br.com.sigvet.sigvetapi.common.repositories.UserRepository;
import br.com.sigvet.sigvetapi.common.usecases.CreateUseCase;
import br.com.sigvet.sigvetapi.feature.client.ClientRepository;

@Component
public class CreateClientUseCase extends UserValidateUseCase implements CreateUseCase<ClientEntity> {

    private final ClientRepository repository;
    
    public CreateClientUseCase(UserRepository userRepository, CityRepository cityRepository,
            ClientRepository repository) {
        super(userRepository, cityRepository);
        this.repository = repository;
    }

    @Transactional
    @Override
    public ClientEntity execute(ClientEntity source) {
        Objects.requireNonNull(source);

        final var errors = validateOnCreate(source);

        if (!errors.isEmpty()) {
            throw new ApplicationException("Client invalid", errors);
        }
        
        return repository.save(source);
    }

}
