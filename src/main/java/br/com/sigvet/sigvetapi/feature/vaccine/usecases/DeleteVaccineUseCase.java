package br.com.sigvet.sigvetapi.feature.vaccine.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.VaccineEntity;
import br.com.sigvet.sigvetapi.common.usecases.DeleteUseCase;
import br.com.sigvet.sigvetapi.feature.vaccine.VaccineRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteVaccineUseCase implements DeleteUseCase<VaccineEntity> {

    private final VaccineRepository repository;

    @Override
    public void execute(Long id) {
        if (repository.existsById(Objects.requireNonNull(id))) {
            repository.deleteById(id);
            return;
        }
        throw new ApplicationException("Vaccine not found");
    }

}
