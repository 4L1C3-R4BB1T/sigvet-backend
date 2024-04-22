package br.com.sigvet.sigvetapi.feature.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.CityRepository;
import br.com.sigvet.sigvetapi.common.CreateUseCase;
import br.com.sigvet.sigvetapi.common.UserRepository;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateClientUseCase implements CreateUseCase<ClientEntity> {

    private final ClientRepository repository;

    private final UserRepository userRepository;

    private final CityRepository cityRepository;
    
    @Transactional
    @Override
    public ClientEntity execute(ClientEntity source) {
        Objects.requireNonNull(source);

        List<String> errors = new ArrayList<>();

        if (userRepository.existsByEmail(source.getEmail())) {
            errors.add("Email is already registered");
        }

        if (userRepository.existsByDocument(source.getDocument())) {
            errors.add("The document is already registered");
        }

        if (userRepository.existsByUsername(source.getDocument())) {
            errors.add("The username is already in use");
        }

        final var city = source.getAddress().getCity();

        if (!cityRepository.existsByNameAndStateId(city.getName(), city.getState().getId())) {
            errors.add("There is no city and state with the information provided");
        }

        if (!errors.isEmpty()) {
            throw new ApplicationException("Client Invalid", errors);
        }

        return repository.save(source);
    }

}
