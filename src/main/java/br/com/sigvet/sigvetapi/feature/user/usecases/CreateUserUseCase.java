package br.com.sigvet.sigvetapi.feature.user.usecases;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.UserEntity;
import br.com.sigvet.sigvetapi.common.entities.enums.Role;
import br.com.sigvet.sigvetapi.feature.user.UserMapper;
import br.com.sigvet.sigvetapi.feature.user.UserRepository;
import br.com.sigvet.sigvetapi.feature.user.UserRequestDTO;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class CreateUserUseCase {

    private final UserMapper userMapper;

    private final UserValidateUseCase userValidateUseCase;

    private final UserRepository repository;
    
    public UserEntity execute(UserRequestDTO record) {
        final var userEntity = userMapper.fromModel(record);
        final var errors = userValidateUseCase.execute(userEntity);

        if (!errors.isEmpty()) {
            throw new ApplicationException("Invalid User", errors);
        }

        userEntity.setRoles(List.of(Role.CLIENT));
        return repository.save(userEntity);
    }
}
