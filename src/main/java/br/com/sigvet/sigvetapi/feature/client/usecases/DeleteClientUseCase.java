package br.com.sigvet.sigvetapi.feature.client.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import br.com.sigvet.sigvetapi.common.repositories.UserRepository;
import br.com.sigvet.sigvetapi.common.usecases.DeleteUseCase;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteClientUseCase implements DeleteUseCase<ClientEntity> {

    private final UserRepository userRepository;

    @Override
    public void execute(Long id) {
        if (userRepository.existsById(Objects.requireNonNull(id))) {
            userRepository.deleteById(id);
            return;
        }

        throw new ApplicationException("Client not found");
    }
    
}
