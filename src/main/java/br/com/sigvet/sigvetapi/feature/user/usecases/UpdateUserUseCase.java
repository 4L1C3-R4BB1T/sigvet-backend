package br.com.sigvet.sigvetapi.feature.user.usecases;

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
            new ApplicationException("Usuário com id %d não encontrado".formatted(id)));

            if (Objects.nonNull(user.getAddress())) {
                addressRepository.deleteByUserId(id);
            }

            final var dto = userMapper.fromModel(source);
            final var notification = userValidateUseCase.execute(user, dto);

            if (notification.hasAnyError()) {
                throw new ApplicationException(notification.getErrors());
            }
            

            userMapper.map(user, dto);
            userRepository.save(user);
            return true;
    }
}
