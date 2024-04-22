package br.com.sigvet.sigvetapi.feature.client;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.AddressRepository;
import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.CityRepository;
import br.com.sigvet.sigvetapi.common.UpdateUseCase;
import br.com.sigvet.sigvetapi.common.UserRepository;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateClientUseCase implements UpdateUseCase<ClientEntity> {

    private final ClientRepository repository;

    private final UserRepository userRepository;

    private final CityRepository cityRepository;

    private final AddressRepository addressRepository;

    private final ClientMapper clientMapper;

    @Override
    public void execute(Long id, ClientEntity source) {
        List<String> errors = new ArrayList<>();

        final var client = repository.findById(Objects.requireNonNull(id));
        final var existsClient = client.isPresent();

        if (!existsClient) {
            errors.add("Client with id %d not found".formatted(id));
        }

        final var city =Objects.requireNonNull(source).getAddress().getCity();

        if (!cityRepository.existsByNameAndStateId(city.getName(), city.getState().getId())) {
            errors.add("There is no city and state with the information provided");
        }

        if (existsClient && userRepository.existsByEmail(source.getEmail())) {
            if (!normalizeString(client.get().getEmail()).equals(normalizeString(source.getEmail()))) {
                errors.add("Email is already registered");
            }   
        }

        if (existsClient && userRepository.existsByDocument(source.getDocument())) {
            if (!normalizeDocument(client.get().getDocument()).equals(normalizeDocument(source.getDocument()))) {
                errors.add("The document is already registered");
            } 
        }

        if (existsClient && userRepository.existsByUsername(source.getUsername())) {
            if (!normalizeString(client.get().getUsername()).equals(normalizeString(source.getUsername()))) {
                errors.add("The username is already in use");
            }   
        }

        if (!errors.isEmpty()) {
            throw new ApplicationException("Client Invalid", errors);
        }

        addressRepository.deleteByUserId(id);

        final var clientEntity = client.get();

        clientMapper.map(clientEntity, source);

        repository.save(clientEntity);
    }

    private String normalizeString(String value) {
        return value.trim().toLowerCase();
    }

    private String normalizeDocument(String document) {
        return document.replaceAll("\\D", "");
    }
}
