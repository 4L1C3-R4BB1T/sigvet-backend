package br.com.sigvet.sigvetapi.feature.photo.usecases;

import java.io.ByteArrayInputStream;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.enums.EntityType;
import br.com.sigvet.sigvetapi.feature.photo.PhotoRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindPhotoUseCase {
    
    private final PhotoRepository repository;

    public InputStreamResource execute(Long entityId, EntityType entityType) {
        final var profile = repository.findByEntityIdAndEntityType(entityId, entityType).orElseThrow(() -> 
        new ApplicationException("There is no photo for this %s".formatted(entityType.getValue())));

        return new InputStreamResource(new ByteArrayInputStream(profile.getData()));
    }
}
