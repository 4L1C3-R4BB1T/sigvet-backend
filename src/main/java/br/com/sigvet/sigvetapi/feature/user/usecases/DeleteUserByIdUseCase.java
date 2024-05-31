package br.com.sigvet.sigvetapi.feature.user.usecases;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.feature.user.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DeleteUserByIdUseCase {
    
    private final UserRepository userRepository;

    @Transactional
    public void execute(Long id) {
        userRepository.findById(id).ifPresent(user -> userRepository.deleteById(id));
    }
}
