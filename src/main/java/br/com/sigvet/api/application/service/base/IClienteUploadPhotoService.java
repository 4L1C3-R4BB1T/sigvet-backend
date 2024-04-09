package br.com.sigvet.api.application.service.base;

import br.com.sigvet.api.application.dto.UploadPhotoRequestDTO;

public interface IClienteUploadPhotoService extends IUploadPhotoService {
    byte[] save(Long id, UploadPhotoRequestDTO record);
    byte[] getPhoto(Long id);
}
