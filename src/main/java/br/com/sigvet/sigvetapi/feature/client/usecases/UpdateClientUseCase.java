package br.com.sigvet.sigvetapi.feature.client.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.UserValidateUseCase;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import br.com.sigvet.sigvetapi.common.repositories.AddressRepository;
import br.com.sigvet.sigvetapi.common.repositories.CityRepository;
import br.com.sigvet.sigvetapi.common.repositories.UserRepository;
import br.com.sigvet.sigvetapi.common.usecases.UpdateUseCase;
import br.com.sigvet.sigvetapi.feature.client.ClientMapper;
import br.com.sigvet.sigvetapi.feature.client.ClientRepository;

@Component
public class UpdateClientUseCase extends UserValidateUseCase implements UpdateUseCase<ClientEntity> {

    private final ClientRepository repository;

    private final AddressRepository addressRepository;

    private final ClientMapper clientMapper;

    public UpdateClientUseCase(UserRepository userRepository, CityRepository cityRepository,
            ClientRepository repository, AddressRepository addressRepository, ClientMapper clientMapper) {
        super(userRepository, cityRepository);
        this.repository = repository;
        this.addressRepository = addressRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    public void execute(Long id, ClientEntity source) {
        final var clientOptional = repository.findById(Objects.requireNonNull(id));

        if (clientOptional.isEmpty()) {
            throw new ApplicationException("Client with id %d not found".formatted(id));
        }

        final var client = clientOptional.get();

        final var errors = validateOnUpdate(client, source);

        if (!errors.isEmpty()) {
            throw new ApplicationException("Client Invalid", errors);
        }

        addressRepository.deleteByUserId(id);

        clientMapper.map(client, source);

        repository.save(client);
    }
}
