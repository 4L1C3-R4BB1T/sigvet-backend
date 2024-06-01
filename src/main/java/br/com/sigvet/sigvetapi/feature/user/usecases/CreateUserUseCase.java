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
        final var notification = userValidateUseCase.execute(userEntity);

        if (notification.hasAnyError()) {
            throw new ApplicationException(notification.getErrors());
        }

        userEntity.setRoles(List.of(Role.UNKNOWN));
        return repository.save(userEntity);
    }
}
