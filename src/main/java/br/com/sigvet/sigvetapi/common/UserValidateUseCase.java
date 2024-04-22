package br.com.sigvet.sigvetapi.common;

import static br.com.sigvet.sigvetapi.common.utils.StringNormalizer.normalizeString;
import static br.com.sigvet.sigvetapi.common.utils.StringNormalizer.removeNonNumericCharacteres;

import java.util.ArrayList;
import java.util.List;

import org.springframework.util.Assert;

import br.com.sigvet.sigvetapi.common.entities.UserEntity;
import br.com.sigvet.sigvetapi.common.repositories.CityRepository;
import br.com.sigvet.sigvetapi.common.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserValidateUseCase {
    
    protected final UserRepository userRepository;

    protected final CityRepository cityRepository;

    public List<String> validateOnCreate(final UserEntity target) {
        Assert.notNull(target, "The target cannot be null");

        final List<String> errors = new ArrayList<>();

        if (userRepository.existsByEmail(target.getEmail())) {
            errors.add("Email is already registered");
        }

        if (userRepository.existsByDocument(target.getDocument())) {
            errors.add("The document is already registered");
        }

        if (userRepository.existsByUsername(target.getDocument())) {
            errors.add("The username is already in use");
        }

        final var city = target.getAddress().getCity();

        if (!cityRepository.existsById(city.getId())) {
            errors.add("There is no city and state with the information provided");
        }

        target.getAddress().setUser(target);

        return errors;
    }

    public List<String> validateOnUpdate(final UserEntity target, final UserEntity source) {
        Assert.notNull(target, "The target cannot be null");
        Assert.notNull(source, "The source cannot be null");

        final List<String> errors = new ArrayList<>();

        if (!cityRepository.existsById(source.getAddress().getCity().getId())) {
            errors.add("There is no city with the information provided");
        }

        if (userRepository.existsByEmail(source.getEmail())) {
            if (!normalizeString(target.getEmail()).equals(normalizeString(source.getEmail()))) {
                errors.add("Email is already registered");
            }
        }

        if (userRepository.existsByDocument(target.getDocument())) {
            if (!removeNonNumericCharacteres(target.getDocument())
                    .equals(removeNonNumericCharacteres(source.getDocument()))) {
                errors.add("The document is already registered");
            }
        }

        if (userRepository.existsByUsername(target.getUsername())) {
            if (!normalizeString(target.getUsername()).equals(normalizeString(source.getUsername()))) {
                errors.add("The username is already in use");
            }
        }

        source.getAddress().setUser(target);

        return errors;
    }
    
}
