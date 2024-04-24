package br.com.sigvet.sigvetapi.feature.vaccine.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.VaccineEntity;
import br.com.sigvet.sigvetapi.common.usecases.FindByIdUseCase;
import br.com.sigvet.sigvetapi.feature.vaccine.VaccineRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindVaccineByIdUseCase implements FindByIdUseCase<VaccineEntity> {

    private final VaccineRepository repository;

    @Override
    public VaccineEntity execute(Long id) {
        return repository.findById(Objects.requireNonNull(id))
            .orElseThrow(() -> new ApplicationException("Vaccine with id %d not found".formatted(id)));
    }

}
