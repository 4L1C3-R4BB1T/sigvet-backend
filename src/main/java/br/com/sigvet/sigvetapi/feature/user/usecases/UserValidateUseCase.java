package br.com.sigvet.sigvetapi.feature.user.usecases;

import static br.com.sigvet.sigvetapi.common.utils.StringNormalizer.normalizeString;
import static br.com.sigvet.sigvetapi.common.utils.StringNormalizer.removeNonNumericCharacteres;

import java.util.List;
import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import br.com.sigvet.sigvetapi.common.Notification;
import br.com.sigvet.sigvetapi.common.entities.UserEntity;
import br.com.sigvet.sigvetapi.common.entities.enums.Role;
import br.com.sigvet.sigvetapi.common.repositories.CityRepository;
import br.com.sigvet.sigvetapi.feature.user.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserValidateUseCase {
    
    private final UserRepository userRepository;

    private final CityRepository cityRepository;

    private final PasswordEncoder passwordEncoder;

    public Notification execute(final UserEntity target) {
        Assert.notNull(target, "The target cannot be null");

        final var notification = new Notification();

        if (userRepository.existsByEmail(normalizeString(target.getEmail()))) {
            notification.addError("Email já em uso");
        }

        if (userRepository.existsByDocument(removeNonNumericCharacteres(target.getDocument()))) {
            notification.addError("Documento já em uso");
        }

        if (userRepository.existsByUsername(normalizeString(target.getUsername()))) {
            notification.addError("Apelido já em uso");
        }

        if (Objects.nonNull(target.getAddress())) {
            if (!cityRepository.existsById(target.getAddress().getCity().getId())) {
                notification.addError("Cidade não encontrada");
            }
            target.getAddress().setUser(target);
        }
        
        target.setRoles(List.of(Role.CLIENT));

        target.setPassword(passwordEncoder.encode(target.getPassword()));

        return notification;
    }

    public Notification execute(final UserEntity target, final UserEntity source) {
        Assert.notNull(target, "The target cannot be null");
        Assert.notNull(source, "The source cannot be null");

        final Notification notification = new Notification();

        if (Objects.nonNull(source.getAddress())) {
            if (!cityRepository.existsById(source.getAddress().getCity().getId())) {
                notification.addError("Não há cidade cadastrada para esse endereço");
            }
            source.getAddress().setUser(target);
        }

        if (userRepository.existsByEmail(normalizeString(source.getEmail()))) {
            if (!normalizeString(target.getEmail()).equals(normalizeString(source.getEmail()))) {
                notification.addError("Email já em uso");
            }
        }


        if (userRepository.existsByDocument(removeNonNumericCharacteres(source.getDocument()))) {
            if (!removeNonNumericCharacteres(target.getDocument())
                    .equals(removeNonNumericCharacteres(source.getDocument()))) {
                notification.addError("Documento já em uso");
            }
        }

        if (userRepository.existsByUsername(normalizeString(source.getUsername()))) {
            if (!normalizeString(target.getUsername()).equals(normalizeString(source.getUsername()))) {
                notification.addError("Apelido já em uso");
            }
        }

        if (Objects.nonNull(source.getPassword())) {
            target.setPassword(passwordEncoder.encode(source.getPassword()));
        }

        return notification;
    }
    
}
