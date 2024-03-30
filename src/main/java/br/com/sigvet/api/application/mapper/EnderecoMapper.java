package br.com.sigvet.api.application.mapper;

import org.springframework.stereotype.Component;

import br.com.sigvet.api.core.domain.entities.Endereco;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.infrastructure.entity.EnderecoEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EnderecoMapper {

    private final CidadeMapper cidadeMapper;

    public EnderecoEntity toEntity(Endereco source) {
        return EnderecoEntity.builder()
            .bairro(source.getBairro())
            .cep(source.getCep())
            .cidade(cidadeMapper.toEntity(source.getCidade()))
            .createdAt(source.getCreatedAt())
            .updatedAt(source.getUpdatedAt())
            .id(source.getId())
            .numero(source.getNumero())
            .rua(source.getRua())
            .build();
    }

    public Endereco toEndereco(EnderecoEntity source) throws DomainInvalidException {
        return new Endereco(
            source.getId(),
            source.getRua(),
            source.getBairro(),
            source.getCep(),
            source.getNumero(),
            cidadeMapper.toCidade(source.getCidade())
        );
    }
}
