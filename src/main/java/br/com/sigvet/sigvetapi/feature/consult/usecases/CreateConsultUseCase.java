package br.com.sigvet.sigvetapi.feature.consult.usecases;

import java.util.Objects;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.sigvetapi.common.entities.ConsultEntity;
import br.com.sigvet.sigvetapi.common.usecases.CreateUseCase;
import br.com.sigvet.sigvetapi.feature.consult.ConsultRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreateConsultUseCase implements CreateUseCase<ConsultEntity> {

    private final ConsultRepository repository;

    @Transactional
    @Override
    public ConsultEntity execute(ConsultEntity source) {
        return repository.save(Objects.requireNonNull(source));
    }

}
