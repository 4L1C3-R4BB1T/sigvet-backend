package br.com.sigvet.sigvetapi.feature.user.usecases;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.repositories.AddressRepository;
import br.com.sigvet.sigvetapi.feature.user.UserMapper;
import br.com.sigvet.sigvetapi.feature.user.UserRepository;
import br.com.sigvet.sigvetapi.feature.user.UserRequestDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateUserUseCase {

    private final UserRepository userRepository;

    private final UserValidateUseCase userValidateUseCase;

    private final UserMapper userMapper;

    private final AddressRepository addressRepository;
    
    @Transactional
    public boolean execute(final Long id, final UserRequestDTO source) {
        final var user = userRepository.findById(id).orElseThrow(() -> 
            new ApplicationException("User Not Found", List.of("User with id %d does not exists")));

            if (Objects.nonNull(user.getAddress())) {
                addressRepository.deleteByUserId(id);
            }

            final var dto = userMapper.fromModel(source);
            final var errors = userValidateUseCase.execute(user, dto);
            if (!errors.isEmpty()) {
                throw new ApplicationException("User Invalid", errors);
            }
            
           

            userMapper.map(user, dto);
            userRepository.save(user);
            return true;
    }
}
