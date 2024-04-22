package br.com.sigvet.sigvetapi.feature.vaccine.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.common.entities.VaccineEntity;
import br.com.sigvet.sigvetapi.common.usecases.CreateUseCase;
import br.com.sigvet.sigvetapi.feature.vaccine.VaccineRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateVaccineUseCase implements CreateUseCase<VaccineEntity> {

    private final VaccineRepository repository;

    @Transactional
    @Override
    public VaccineEntity execute(VaccineEntity source) {
        return repository.save(Objects.requireNonNull(source));
    }

}
