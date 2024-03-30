package br.com.sigvet.api.application.mapper;

import org.springframework.stereotype.Component;

import br.com.sigvet.api.core.domain.entities.UF;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.infrastructure.entity.UFEntity;

@Component
public class UFMapper {

    public UFEntity toEntity(UF source) {
        return UFEntity.builder()
            .nome(source.getNome())
            .sigla(source.getSigla())
            .build();
    }

    public UF toUF(UFEntity source) throws DomainInvalidException {
        return new UF(source.getSigla(), source.getNome());
    }

}
