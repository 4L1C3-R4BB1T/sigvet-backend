package br.com.sigvet.sigvetapi.feature.user.usecases;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.feature.user.UserRepository;
import br.com.sigvet.sigvetapi.feature.user.UserRepository.UserResponseProjection;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class FindUserByViewerRoleUseCase {

    private final UserRepository userRepository;
    
    public List<UserResponseProjection> execute() {
        return userRepository.findByViewerRole();
    }
}
