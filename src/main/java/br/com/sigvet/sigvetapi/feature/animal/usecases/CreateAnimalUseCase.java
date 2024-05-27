package br.com.sigvet.sigvetapi.feature.animal.usecases;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import br.com.sigvet.sigvetapi.common.usecases.CreateUseCase;
import br.com.sigvet.sigvetapi.feature.animal.AnimalRepository;
import br.com.sigvet.sigvetapi.feature.client.ClientRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateAnimalUseCase implements CreateUseCase<AnimalEntity> {

    private final AnimalRepository repository;

    private final ClientRepository clientRepository;

    @Transactional
    @Override
    public AnimalEntity execute(AnimalEntity source) {
        final var clientId = source.getClient().getId();

        if (!clientRepository.existsById(clientId)) {
            throw new ApplicationException("Cliente com id %d não foi encontrado".formatted(clientId));
        }

        return repository.save(Objects.requireNonNull(source));
    }
    
}
