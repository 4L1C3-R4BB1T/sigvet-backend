package br.com.sigvet.sigvetapi.feature.animal.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import br.com.sigvet.sigvetapi.common.usecases.UpdateUseCase;
import br.com.sigvet.sigvetapi.feature.animal.AnimalMapper;
import br.com.sigvet.sigvetapi.feature.animal.AnimalRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateAnimalUseCase implements UpdateUseCase<AnimalEntity> {

    private final AnimalRepository repository;

    private final AnimalMapper animalMapper;

    @Override
    public void execute(Long id, AnimalEntity source) {
        final var animalOptional = repository.findById(Objects.requireNonNull(id));

        if (animalOptional.isEmpty()) {
            throw new ApplicationException("Animal with id %d not found".formatted(id));
        }

        final var animal = animalOptional.get();

        if (animal.getClient().getId() != source.getClient().getId()) {
            throw new ApplicationException("Changing animal identification is not permitted");
        }

        animalMapper.map(animal, source);
        repository.save(animal);
    }

}
