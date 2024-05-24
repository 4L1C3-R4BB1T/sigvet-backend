package br.com.sigvet.sigvetapi.feature.animal.usecases;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import br.com.sigvet.sigvetapi.common.entities.enums.EntityType;
import br.com.sigvet.sigvetapi.common.usecases.FindByIdUseCase;
import br.com.sigvet.sigvetapi.feature.animal.AnimalRepository;
import br.com.sigvet.sigvetapi.feature.photo.usecases.FindPhotoUseCase;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAnimalByIdUseCase implements FindByIdUseCase<AnimalEntity> {

    private final AnimalRepository repository;

    private final FindPhotoUseCase findPhotoUseCase;

    @Override
    public AnimalEntity execute(Long id) {
        final var animal = repository.findById(Objects.requireNonNull(id))
            .orElseThrow(() -> new ApplicationException("Animal not found", List.of("Animal com id %d não encontrado".formatted(id))));
        try {
            findPhotoUseCase.execute(id, EntityType.ANIMAL);
            animal.setPhotoUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/photo/animal/{id}").buildAndExpand(id).toString());
        } catch (Exception ex) {}

        return animal;
    }
    
}
