package br.com.sigvet.sigvetapi.feature.consult.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.ConsultEntity;
import br.com.sigvet.sigvetapi.common.usecases.DeleteUseCase;
import br.com.sigvet.sigvetapi.feature.consult.ConsultRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteConsultUseCase implements DeleteUseCase<ConsultEntity> {

    private final ConsultRepository repository;

    @Override
    public void execute(Long id) {
        if (repository.existsById(Objects.requireNonNull(id))) {
            repository.deleteById(id);
            return;
        }
        throw new ApplicationException("Consult with id %d not found".formatted(id));
    }

}
