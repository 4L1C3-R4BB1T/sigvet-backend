package br.com.sigvet.sigvetapi.feature.vaccine.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.VaccineEntity;
import br.com.sigvet.sigvetapi.common.usecases.UpdateUseCase;
import br.com.sigvet.sigvetapi.feature.vaccine.VaccineMapper;
import br.com.sigvet.sigvetapi.feature.vaccine.VaccineRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateVaccineUseCase implements UpdateUseCase<VaccineEntity> {

    private final VaccineRepository repository;

    private final VaccineMapper vaccineMapper;

    @Override
    public void execute(Long id, VaccineEntity source) {
        final var vaccineOptional = repository.findById(Objects.requireNonNull(id));

        if (vaccineOptional.isEmpty()) {
            throw new ApplicationException("Vacina com id %d não encontrada".formatted(id));
        }

        final var vaccine = vaccineOptional.get();

        vaccineMapper.map(vaccine, source);
        repository.save(vaccine);
    }

}
