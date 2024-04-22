package br.com.sigvet.sigvetapi.feature.animal.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import br.com.sigvet.sigvetapi.common.usecases.FindByIdUseCase;
import br.com.sigvet.sigvetapi.feature.animal.AnimalRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAnimalByIdUseCase implements FindByIdUseCase<AnimalEntity> {

    private final AnimalRepository repository;

    @Override
    public AnimalEntity execute(Long id) {
        return repository.findById(Objects.requireNonNull(id))
            .orElseThrow(() -> new ApplicationException("Animal with %d not found".formatted(id)));
    }
    
}
