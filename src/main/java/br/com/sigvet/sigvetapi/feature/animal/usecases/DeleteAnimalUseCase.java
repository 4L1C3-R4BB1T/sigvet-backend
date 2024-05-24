package br.com.sigvet.sigvetapi.feature.animal.usecases;

import java.util.List;
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

    private final AnimalRepository repository;

    @Override
    public void execute(Long id) {
        if (repository.existsById(Objects.requireNonNull(id))) {
            repository.deleteById(id);
            return;
        }
        throw new ApplicationException("Animal Not Found", List.of("Animal com id %d não foi encontrado".formatted(id)));
    }
    
}
