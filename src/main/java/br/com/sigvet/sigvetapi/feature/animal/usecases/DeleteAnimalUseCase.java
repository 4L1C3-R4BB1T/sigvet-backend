package br.com.sigvet.sigvetapi.feature.animal.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import br.com.sigvet.sigvetapi.common.usecases.DeleteUseCase;
import br.com.sigvet.sigvetapi.feature.animal.AnimalRepository;
import br.com.sigvet.sigvetapi.feature.vaccination.VaccinationRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteAnimalUseCase implements DeleteUseCase<AnimalEntity> {

    private final AnimalRepository repository;

    private final VaccinationRepository vaccinationRepository;

    @Transactional
    @Override
    public void execute(Long id) {
        if (repository.existsById(Objects.requireNonNull(id))) {
            vaccinationRepository.deleteByAnimalId(id);
            repository.deleteById(id);
            return;
        }
        throw new ApplicationException("Animal com id %d não foi encontrado".formatted(id));
    }
    
}
