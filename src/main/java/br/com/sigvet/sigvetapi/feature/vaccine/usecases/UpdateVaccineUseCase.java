package br.com.sigvet.sigvetapi.feature.vaccine.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.VaccineEntity;
import br.com.sigvet.sigvetapi.common.usecases.UpdateUseCase;
import br.com.sigvet.sigvetapi.feature.vaccine.VaccineRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateVaccineUseCase implements UpdateUseCase<VaccineEntity> {

    private final VaccineRepository repository;

    @Override
    public void execute(Long id, VaccineEntity source) {
        final var vaccineOptional = repository.findById(Objects.requireNonNull(id));

        if (vaccineOptional.isEmpty()) {
            throw new ApplicationException("Vaccine with id %d not found".formatted(id));
        }

        final var vaccine = vaccineOptional.get();

        repository.save(vaccine);
    }

}
