package br.com.sigvet.sigvetapi.feature.animal.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import br.com.sigvet.sigvetapi.common.usecases.DeleteUseCase;
import br.com.sigvet.sigvetapi.feature.animal.AnimalRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteAnimalUseCase implements DeleteUseCase<AnimalEntity> {

    private final AnimalRepository animalRepository;

    @Override
    public void execute(Long id) {
       if (animalRepository.existsById(Objects.requireNonNull(id))) {
            animalRepository.deleteById(id);
            return;
       }

       throw new ApplicationException("Animal not found");

    }
}
