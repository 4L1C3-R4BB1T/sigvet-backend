package br.com.sigvet.sigvetapi.feature.client.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import br.com.sigvet.sigvetapi.common.repositories.AddressRepository;
import br.com.sigvet.sigvetapi.common.usecases.UpdateUseCase;
import br.com.sigvet.sigvetapi.feature.client.ClientMapper;
import br.com.sigvet.sigvetapi.feature.client.ClientRepository;
import br.com.sigvet.sigvetapi.feature.user.usecases.UserValidateUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UpdateClientUseCase implements UpdateUseCase<ClientEntity> {

    private final ClientRepository repository;

    private final AddressRepository addressRepository;

    private final ClientMapper clientMapper;

    private final UserValidateUseCase userValidateUseCase;

    @Override
    public void execute(Long id, ClientEntity source) {
        final var clientOptional = repository.findById(Objects.requireNonNull(id));

        if (clientOptional.isEmpty()) {
            throw new ApplicationException("Cliente com id %d não encontrado".formatted(id));
        }

        final var client = clientOptional.get();

        if (Objects.nonNull(client.getAddress())) {
            addressRepository.deleteByUserId(id);
        }

        final var notification = userValidateUseCase.execute(client, source);

        if (notification.hasAnyError()) {
            throw new ApplicationException("Client Invalid", notification.getErrors());
        }

        clientMapper.map(client, source);
        repository.save(client);
    }
    
}
