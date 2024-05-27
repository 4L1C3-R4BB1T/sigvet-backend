package br.com.sigvet.sigvetapi.feature.vaccination.usecases;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.VaccinationEntity;
import br.com.sigvet.sigvetapi.common.usecases.FindByIdUseCase;
import br.com.sigvet.sigvetapi.feature.vaccination.VaccinationRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindVaccinationByIdUseCase implements FindByIdUseCase<VaccinationEntity> {

    private final VaccinationRepository repository;

    @Override
    public VaccinationEntity execute(Long id) {
        return repository.findById(Objects.requireNonNull(id))
            .orElseThrow(() -> new ApplicationException("Vacinação com id %d não encontrada".formatted(id)));
    }

}
