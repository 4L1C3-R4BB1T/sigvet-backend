package br.com.sigvet.sigvetapi.feature.vaccination.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.VaccinationEntity;
import br.com.sigvet.sigvetapi.common.usecases.DeleteUseCase;
import br.com.sigvet.sigvetapi.feature.vaccination.VaccinationRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteVaccinationUseCase implements DeleteUseCase<VaccinationEntity> {

    private final VaccinationRepository repository;

    @Override
    public void execute(Long id) {
        if (repository.existsById(Objects.requireNonNull(id))) {
            repository.deleteById(id);
            return;
        }
        throw new ApplicationException("Vacinação com id %d não encontrada".formatted(id));
    }

}
