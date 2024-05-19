package br.com.sigvet.sigvetapi.feature.animal.usecases;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import br.com.sigvet.sigvetapi.feature.animal.AnimalRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SearchAnimalByNameUseCase {

    private final AnimalRepository animalRepository;
    
    public List<AnimalEntity> execute(String name) {
       return animalRepository.searchByName(name);
    }
}
