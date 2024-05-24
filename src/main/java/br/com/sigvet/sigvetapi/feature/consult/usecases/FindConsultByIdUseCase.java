package br.com.sigvet.sigvetapi.feature.consult.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.ConsultEntity;
import br.com.sigvet.sigvetapi.common.usecases.FindByIdUseCase;
import br.com.sigvet.sigvetapi.feature.consult.ConsultRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindConsultByIdUseCase implements FindByIdUseCase<ConsultEntity> {

    private final ConsultRepository repository;

    @Override
    public ConsultEntity execute(Long id) {
        return repository.findById(Objects.requireNonNull(id))
            .orElseThrow(() -> new ApplicationException("Consulta com id %d não encontrado".formatted(id)));
    }

}
