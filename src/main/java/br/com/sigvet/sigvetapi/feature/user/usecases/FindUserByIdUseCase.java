package br.com.sigvet.sigvetapi.feature.user.usecases;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.UserEntity;
import br.com.sigvet.sigvetapi.feature.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindUserByIdUseCase {

    private final UserRepository userRepository;
    
    public UserEntity execute(Long id) {
        return userRepository.findById(id).orElseThrow(() -> 
            new ApplicationException("Usuário com id %d não encontrado".formatted(id)));
    }
}
