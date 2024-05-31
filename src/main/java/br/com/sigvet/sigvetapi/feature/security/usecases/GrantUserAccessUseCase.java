package br.com.sigvet.sigvetapi.feature.security.usecases;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.common.entities.enums.Role;
import br.com.sigvet.sigvetapi.feature.client.ClientRepository;
import br.com.sigvet.sigvetapi.feature.user.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class GrantUserAccessUseCase {

    private final ClientRepository clientRepository;

    private final UserRepository userRepository;
    
    @Transactional
    public void execute(Long userId) {
        userRepository.findById(userId)
            .ifPresent(user -> {
                user.getRoles().add(Role.CLIENT);
                clientRepository.saveByUserId(userId);
        });
    }
}
