package br.com.sigvet.sigvetapi.feature.client.usecases;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import br.com.sigvet.sigvetapi.common.entities.enums.EntityType;
import br.com.sigvet.sigvetapi.feature.client.ClientRepository;
import br.com.sigvet.sigvetapi.feature.photo.usecases.FindPhotoUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SearchClientByNameUseCase {

    private final ClientRepository clientRepository;

    private final FindPhotoUseCase findPhotoUseCase;
    
    public List<ClientEntity> execute(String name) {
        return clientRepository.searchByName(name)
        .stream()
        .map(user -> {
              try {
                findPhotoUseCase.execute(user.getId(), EntityType.USER);
                user.setPhotoUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/photo/user/{id}").buildAndExpand(user.getId()).toString());
            } catch (Exception ex) {}
            return user;
        })
        .toList();
        
    }
}
