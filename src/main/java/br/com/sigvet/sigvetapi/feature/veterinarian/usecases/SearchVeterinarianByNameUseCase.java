package br.com.sigvet.sigvetapi.feature.veterinarian.usecases;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sigvet.sigvetapi.common.entities.VeterinarianEntity;
import br.com.sigvet.sigvetapi.common.entities.enums.EntityType;
import br.com.sigvet.sigvetapi.feature.photo.usecases.FindPhotoUseCase;
import br.com.sigvet.sigvetapi.feature.veterinarian.VeterinarianRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SearchVeterinarianByNameUseCase {
    
    private final VeterinarianRepository repository;

    private final FindPhotoUseCase findPhotoUseCase;

    public List<VeterinarianEntity> execute(final String name) {
        return repository.searchByName(name)
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


