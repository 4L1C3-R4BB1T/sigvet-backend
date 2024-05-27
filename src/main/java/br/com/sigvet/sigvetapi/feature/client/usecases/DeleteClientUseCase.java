package br.com.sigvet.sigvetapi.feature.client.usecases;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.Notification;
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
    public void execute(final Long id) {
        final var notification = new Notification();
        final var user = clientRepository.findById(id);

        if (user.isEmpty()) {
            notification.addError("Cliente com id %d não encontrado".formatted(id));
        }

        if (user.isPresent()) {
            final var animals = user.get().getAnimals();
            if (Objects.nonNull(animals) && !animals.isEmpty()) {
                notification.addError("Este cliente possui animais; remova-os primeiro");
            }
        }

        if (notification.hasAnyError()) {
            throw new ApplicationException(notification.getErrors());
        }

        clientRepository.deleteById(id);
    }
    
}
