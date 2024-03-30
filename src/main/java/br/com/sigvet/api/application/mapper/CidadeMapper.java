package br.com.sigvet.api.application.mapper;

import org.springframework.stereotype.Component;

import br.com.sigvet.api.core.domain.entities.Cidade;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.infrastructure.entity.CidadeEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CidadeMapper {

    private final UFMapper ufMapper;

    public CidadeEntity toEntity(Cidade source) {
        return CidadeEntity.builder()
            .createdAt(source.getCreatedAt())
            .updatedAt(source.getUpdatedAt())
            .id(source.getId())
            .nome(source.getNome())
            .uf(ufMapper.toEntity(source.getUf()))
            .build();
    }

    public Cidade toCidade(CidadeEntity source) throws DomainInvalidException {
        return new Cidade(source.getId(), source.getNome(), ufMapper.toUF(source.getUf()));
    }
}
