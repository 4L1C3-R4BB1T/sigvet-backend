package br.com.sigvet.sigvetapi.feature.diagnostic.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.DiagnosticEntity;
import br.com.sigvet.sigvetapi.common.usecases.UpdateUseCase;
import br.com.sigvet.sigvetapi.feature.diagnostic.DiagnosticMapper;
import br.com.sigvet.sigvetapi.feature.diagnostic.DiagnosticRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UpdateDiagnosticUseCase implements UpdateUseCase<DiagnosticEntity> {

    private final DiagnosticRepository repository;

    private final DiagnosticMapper diagnosticMapper;

    @Override
    public void execute(Long id, DiagnosticEntity source) {
        final var diagnosticOptional = repository.findById(Objects.requireNonNull(id));

        if (diagnosticOptional.isEmpty()) {
            throw new ApplicationException("Diagnostic with id %d not found".formatted(id));
        }

        final var diagnostic = diagnosticOptional.get();

        if (diagnostic.getConsult().getId() != source.getConsult().getId()) {
            throw new ApplicationException("Cannot be changing the consult id");
        }

        diagnosticMapper.map(diagnostic, source);
        repository.save(diagnostic);
    }

}
