package br.com.sigvet.api.application.service.base;

import br.com.sigvet.api.application.dto.UploadPhotoRequestDTO;
import br.com.sigvet.api.application.enums.EntityType;

public interface IUploadPhotoService {
    byte[] getPhoto(Long entityId, EntityType entityType);
    byte[] save(Long entityId, UploadPhotoRequestDTO photoUpload, EntityType entityType);
}
