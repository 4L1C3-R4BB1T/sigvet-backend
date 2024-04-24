package br.com.sigvet.sigvetapi.feature.diagnostic.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.DiagnosticEntity;
import br.com.sigvet.sigvetapi.common.entities.enums.ConsultationStatus;
import br.com.sigvet.sigvetapi.common.usecases.CreateUseCase;
import br.com.sigvet.sigvetapi.feature.consult.ConsultRepository;
import br.com.sigvet.sigvetapi.feature.diagnostic.DiagnosticRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateDiagnosticUseCase implements CreateUseCase<DiagnosticEntity> {

    private final DiagnosticRepository repository;

    private final ConsultRepository consultRepository;

    @Transactional
    @Override
    public DiagnosticEntity execute(DiagnosticEntity source) {
        final var consultId = source.getConsult().getId();

        if (!consultRepository.existsById(consultId)) {
            throw new ApplicationException("Consult with id %d not found".formatted(consultId));
        }

        // O sistema altera o status da consulta para "concluído"
        final var consult = consultRepository.findById(consultId);
        consult.get().setStatus(ConsultationStatus.COMPLETED);

        return repository.save(Objects.requireNonNull(source));
    }

}
