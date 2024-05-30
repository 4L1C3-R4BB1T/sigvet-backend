package br.com.sigvet.sigvetapi.feature.animal.usecases;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import br.com.sigvet.sigvetapi.common.entities.enums.EntityType;
import br.com.sigvet.sigvetapi.feature.animal.AnimalRepository;
import br.com.sigvet.sigvetapi.feature.photo.usecases.FindPhotoUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SearchAnimalByNameUseCase {

    private final AnimalRepository animalRepository;

    private final FindPhotoUseCase findPhotoUseCase;
    
    public List<AnimalEntity> execute(String name) {
       return animalRepository.searchByName(name)
        .stream()
        .map(animal -> {
            try {
                findPhotoUseCase.execute(animal.getId(), EntityType.ANIMAL);
                animal.setPhotoUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/photo/animal/{id}").buildAndExpand(animal.getId()).toString());
            } catch (Exception ex) {}
            return animal;
        }).toList();
    }
}
