package br.com.sigvet.sigvetapi.feature.vaccination.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.common.entities.VaccinationEntity;
import br.com.sigvet.sigvetapi.common.usecases.CreateUseCase;
import br.com.sigvet.sigvetapi.feature.vaccination.VaccinationRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateVaccinationUseCase implements CreateUseCase<VaccinationEntity> {

    private final VaccinationRepository repository;

    @Transactional
    @Override
    public VaccinationEntity execute(VaccinationEntity source) {
        return repository.save(Objects.requireNonNull(source));
    }

}
