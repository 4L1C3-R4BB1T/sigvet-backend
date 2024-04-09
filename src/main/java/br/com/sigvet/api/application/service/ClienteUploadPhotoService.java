package br.com.sigvet.api.application.service;

import org.springframework.stereotype.Service;

import br.com.sigvet.api.application.dto.UploadPhotoRequestDTO;
import br.com.sigvet.api.application.enums.EntityType;
import br.com.sigvet.api.application.service.base.IClienteUploadPhotoService;
import br.com.sigvet.api.application.service.base.BaseUploadPhotoService;
import br.com.sigvet.api.infrastructure.repository.FotoJpaRepository;

@Service
public class ClienteUploadPhotoService extends BaseUploadPhotoService implements IClienteUploadPhotoService {

    public ClienteUploadPhotoService(FotoJpaRepository repository) {
        super(repository);
    }

    @Override
    public byte[] save(Long id, UploadPhotoRequestDTO record) {
        return super.save(id, record, EntityType.USUARIO);
    }

    @Override
    public byte[] getPhoto(Long id) {
        return super.getPhoto(id, EntityType.USUARIO);
    }
    
}
