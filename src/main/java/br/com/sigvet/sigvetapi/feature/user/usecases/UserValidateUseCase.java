package br.com.sigvet.sigvetapi.feature.user.usecases;

import static br.com.sigvet.sigvetapi.common.utils.StringNormalizer.normalizeString;
import static br.com.sigvet.sigvetapi.common.utils.StringNormalizer.removeNonNumericCharacteres;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import br.com.sigvet.sigvetapi.common.entities.UserEntity;
import br.com.sigvet.sigvetapi.common.entities.enums.Role;
import br.com.sigvet.sigvetapi.common.repositories.CityRepository;
import br.com.sigvet.sigvetapi.common.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserValidateUseCase {
    
    protected final UserRepository userRepository;

    protected final CityRepository cityRepository;

    protected final PasswordEncoder passwordEncoder;

    public List<String> validateOnCreate(final UserEntity target) {
        Assert.notNull(target, "The target cannot be null");

        final List<String> errors = new ArrayList<>();

        if (userRepository.existsByEmail(normalizeString(target.getEmail()))) {
            errors.add("Email is already registered");
        }

        if (userRepository.existsByDocument(removeNonNumericCharacteres(target.getDocument()))) {
            errors.add("The document is already registered");
        }

        if (userRepository.existsByUsername(normalizeString(target.getUsername()))) {
            errors.add("The username is already in use");
        }

        if (Objects.nonNull(target.getAddress())) {
            if (!cityRepository.existsById(target.getAddress().getCity().getId())) {
                errors.add("There is no city and state with the information provided");
            }
            target.getAddress().setUser(target);
        }
        
        target.setRoles(List.of(Role.CLIENT));

        target.setPassword(passwordEncoder.encode(target.getPassword()));

        return errors;
    }

    public List<String> validateOnUpdate(final UserEntity target, final UserEntity source) {
        Assert.notNull(target, "The target cannot be null");
        Assert.notNull(source, "The source cannot be null");

        final List<String> errors = new ArrayList<>();

        if (Objects.nonNull(source.getAddress())) {
            if (!cityRepository.existsById(source.getAddress().getCity().getId())) {
                errors.add("There is no city with the information provided");
            }
            source.getAddress().setUser(target);
        }

        if (userRepository.existsByEmail(normalizeString(source.getEmail()))) {
            if (!normalizeString(target.getEmail()).equals(normalizeString(source.getEmail()))) {
                errors.add("Email is already registered");
            }
        }


        if (userRepository.existsByDocument(removeNonNumericCharacteres(source.getDocument()))) {
            if (!removeNonNumericCharacteres(target.getDocument())
                    .equals(removeNonNumericCharacteres(source.getDocument()))) {
                errors.add("The document is already registered");
            }
        }

        if (userRepository.existsByUsername(normalizeString(source.getUsername()))) {
            if (!normalizeString(target.getUsername()).equals(normalizeString(source.getUsername()))) {
                errors.add("The username is already in use");
            }
        }

        if (Objects.nonNull(source.getPassword())) {
            target.setPassword(passwordEncoder.encode(source.getPassword()));
        }

        return errors;
    }
    
}