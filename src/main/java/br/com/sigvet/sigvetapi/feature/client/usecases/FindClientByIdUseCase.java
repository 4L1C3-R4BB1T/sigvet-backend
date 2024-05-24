package br.com.sigvet.sigvetapi.feature.client.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import br.com.sigvet.sigvetapi.common.entities.enums.EntityType;
import br.com.sigvet.sigvetapi.common.usecases.FindByIdUseCase;
import br.com.sigvet.sigvetapi.feature.client.ClientRepository;
import br.com.sigvet.sigvetapi.feature.photo.usecases.FindPhotoUseCase;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindClientByIdUseCase implements FindByIdUseCase<ClientEntity> {

    private final ClientRepository repository;

    private final FindPhotoUseCase findPhotoUseCase;

    @Override
    public ClientEntity execute(Long id) {

        final var client = repository.findById(Objects.requireNonNull(id))
            .orElseThrow(() -> new ApplicationException("Cliente com id %d não encontrado".formatted(id)));
        try {
            findPhotoUseCase.execute(id, EntityType.USER);
            client.setPhotoUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/photo/user/{id}").buildAndExpand(id).toString());
        } catch (Exception ex) {}

        return client;
    }
    
}
