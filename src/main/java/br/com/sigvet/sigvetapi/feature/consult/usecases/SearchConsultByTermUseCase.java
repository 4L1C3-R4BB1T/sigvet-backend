package br.com.sigvet.sigvetapi.feature.consult.usecases;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.entities.ConsultEntity;
import br.com.sigvet.sigvetapi.feature.consult.ConsultRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SearchConsultByTermUseCase {
    
    private final ConsultRepository repository;

    public List<ConsultEntity> execute(String term) {
        return repository.search(term);
    }
}
