package br.com.sigvet.sigvetapi.feature.user.usecases;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.UserEntity;
import br.com.sigvet.sigvetapi.common.entities.enums.Role;
import br.com.sigvet.sigvetapi.common.repositories.CityRepository;
import br.com.sigvet.sigvetapi.common.repositories.UserRepository;
import br.com.sigvet.sigvetapi.feature.user.UserMapper;
import br.com.sigvet.sigvetapi.feature.user.UserRequestDTO;

@Component
public class CreateUserUseCase extends UserValidateUseCase {

    private final UserMapper userMapper;


    public CreateUserUseCase(CityRepository cityRepository, PasswordEncoder passwordEncoder, UserRepository userRepository, UserMapper userMapper) {
        super(userRepository, cityRepository, passwordEncoder);
        this.userMapper = userMapper;
    }
    
    public UserEntity execute(UserRequestDTO record) {
        final var userEntity = userMapper.fromModel(record);
        final var errors = validateOnCreate(userEntity);

        if (!errors.isEmpty()) {
            throw new ApplicationException("Invalid User", errors);
        }

        userEntity.setRoles(List.of(Role.CLIENT, Role.ADMIN));
        return userRepository.save(userEntity);
    }
}
