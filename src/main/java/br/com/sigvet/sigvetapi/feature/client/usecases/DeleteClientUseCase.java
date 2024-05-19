package br.com.sigvet.sigvetapi.feature.client.usecases;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import br.com.sigvet.sigvetapi.common.usecases.DeleteUseCase;
import br.com.sigvet.sigvetapi.feature.client.ClientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteClientUseCase implements DeleteUseCase<ClientEntity> {

    private final ClientRepository clientRepository;

    @Transactional
    @Override
    public void execute(Long id) {
        System.out.println("OIIIIIIIIIIIIIIIIIIIIII");
        final var errors = new ArrayList<String>();
        final var user = clientRepository.findById(id);

        if (user.isEmpty()) {
            errors.add("Client with id %d not found".formatted(id));
        }

        if (user.isPresent()) {
            final var animals = user.get().getAnimals();
            if (Objects.nonNull(animals) && !animals.isEmpty()) {
                errors.add("Client has pets, first delete them");
            }
        }

        if (!errors.isEmpty()) {
            throw new ApplicationException("Client Invalid", errors);
        }

        clientRepository.deleteById(id);
    }
    
}
