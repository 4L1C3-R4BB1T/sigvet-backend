package br.com.sigvet.sigvetapi.feature.diagnostic.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.DiagnosticEntity;
import br.com.sigvet.sigvetapi.common.usecases.FindByIdUseCase;
import br.com.sigvet.sigvetapi.feature.diagnostic.DiagnosticRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindDiagnosticByIdUseCase implements FindByIdUseCase<DiagnosticEntity> {

    private final DiagnosticRepository repository;

    @Override
    public DiagnosticEntity execute(Long id) {
        return repository.findById(Objects.requireNonNull(id))
            .orElseThrow(() -> new ApplicationException("Diagnostic with id %d not found".formatted(id)));
    }

}
