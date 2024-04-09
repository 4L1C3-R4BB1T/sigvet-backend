package br.com.sigvet.api.application.service.base;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import br.com.sigvet.api.application.dto.UploadPhotoRequestDTO;
import br.com.sigvet.api.application.enums.EntityType;
import br.com.sigvet.api.application.exception.PhotoNotFoundException;
import br.com.sigvet.api.application.exception.UploadException;
import br.com.sigvet.api.infrastructure.entity.FotoEntity;
import br.com.sigvet.api.infrastructure.repository.FotoJpaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BaseUploadPhotoService implements IUploadPhotoService {

    private final FotoJpaRepository repository;

    @Transactional
    @Override
    public byte[] save(Long entityId, UploadPhotoRequestDTO photoUpload, EntityType entityType)  {
        try {
            repository.queryByEntidadeId(entityId).ifPresent(foto -> repository.deleteById(foto.getId()));

            MultipartFile file = photoUpload.file();

            byte[] fileBytes = file.getBytes();

            FotoEntity fotoEntity = FotoEntity
                .builder()
                .dados(fileBytes)
                .entidadeId(entityId)
                .entidadeTipo(entityType.getValue())
                .tipoMime(file.getContentType())
                .build();

            repository.save(fotoEntity);

            return fileBytes;
    
        } catch (Exception exception) {
            throw new UploadException("Não foi possível efetuar o upload do arquivo");
        }
    }

    @Override
    public byte[] getPhoto(Long entityId, EntityType entityType) {
        return repository
            .queryByEntidadeIdAndEntidadeTipo(entityId, entityType.getValue())
            .orElseThrow(() -> new PhotoNotFoundException("Foto não encontrada"))
            .getDados();
    }

}
