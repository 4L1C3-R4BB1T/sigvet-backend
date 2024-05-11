package br.com.sigvet.sigvetapi.feature.photo.usecases;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.PhotoEntity;
import br.com.sigvet.sigvetapi.common.entities.enums.EntityType;
import br.com.sigvet.sigvetapi.feature.photo.PhotoMapper;
import br.com.sigvet.sigvetapi.feature.photo.PhotoRepository;
import br.com.sigvet.sigvetapi.feature.photo.PhotoResponseDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SetPhotoUseCase {


    private final PhotoRepository repository;

    private final PhotoMapper photoMapper;
    
    @Transactional 
    public PhotoResponseDTO execute(Long entityId, MultipartFile multipartFile, EntityType entityType) {
        try {

            if (repository.existsByEntityIdAndEntityType(entityId, entityType)) {
                repository.delete(repository.findByEntityIdAndEntityType(entityId, entityType).get());
            }

            final var photoEntity = PhotoEntity.builder()
                .entityId(entityId) 
                .entityType(entityType)
                .contentType(multipartFile.getContentType())
                .fileName(multipartFile.getOriginalFilename())
                .size(multipartFile.getSize())
                .data(multipartFile.getBytes())
                .build();

            return photoMapper.map(repository.save(photoEntity));
        } catch (Exception exception) {
            throw new ApplicationException("Unable to save photo file");
        }
    }
}
