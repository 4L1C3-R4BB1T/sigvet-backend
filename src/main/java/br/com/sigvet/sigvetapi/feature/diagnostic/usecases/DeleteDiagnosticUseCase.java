package br.com.sigvet.sigvetapi.feature.diagnostic.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.DiagnosticEntity;
import br.com.sigvet.sigvetapi.common.usecases.DeleteUseCase;
import br.com.sigvet.sigvetapi.feature.diagnostic.DiagnosticRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DeleteDiagnosticUseCase implements DeleteUseCase<DiagnosticEntity> {

    private final DiagnosticRepository repository;

    @Override
    public void execute(Long id) {
        if (repository.existsById(Objects.requireNonNull(id))) {
            repository.deleteById(id);
            return;
        }
        throw new ApplicationException("Diagnóstico com id %d não encontrado".formatted(id));
    }

}
