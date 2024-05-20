package br.com.sigvet.sigvetapi.feature.photo.usecases;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.common.entities.enums.EntityType;
import br.com.sigvet.sigvetapi.feature.photo.PhotoRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DeletePhotoUseCase {

    private final PhotoRepository photoRepository;
    
    @Transactional
    public boolean execute(Long id, EntityType entityType) {
        return photoRepository.deleteByEntityIdAndEntityType(id, entityType) > 0;
    }
}
