package br.com.sigvet.sigvetapi.feature.diagnostic.usecases;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.entities.DiagnosticEntity;
import br.com.sigvet.sigvetapi.feature.diagnostic.DiagnosticRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SearchDiagnosticByTermUseCase {
    
    private final DiagnosticRepository repository;

    public List<DiagnosticEntity> execute(String term) {
        return repository.searchByTerm(term);
    }
}
