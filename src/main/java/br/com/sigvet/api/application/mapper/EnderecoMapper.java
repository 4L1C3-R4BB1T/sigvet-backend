package br.com.sigvet.api.application.mapper;

import org.springframework.stereotype.Component;

import br.com.sigvet.api.core.domain.entities.Cidade;
import br.com.sigvet.api.core.domain.entities.Endereco;
import br.com.sigvet.api.core.domain.entities.Usuario;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.infrastructure.entity.CidadeEntity;
import br.com.sigvet.api.infrastructure.entity.EnderecoEntity;
import br.com.sigvet.api.infrastructure.entity.UsuarioEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EnderecoMapper {

    public EnderecoEntity toEntity(Endereco source, UsuarioEntity usuarioEntity, CidadeEntity cidadeEntity) {
        return EnderecoEntity.builder()
            .bairro(source.getBairro())
            .cep(source.getCep())
            .cidade(cidadeEntity)
            .createdAt(source.getCreatedAt())
            .updatedAt(source.getUpdatedAt())
            .id(source.getId())
            .numero(source.getNumero())
            .rua(source.getRua())
            .usuario(usuarioEntity)
            .build();
    }

    public Endereco toEndereco(EnderecoEntity source, Usuario usuario, Cidade cidade) throws DomainInvalidException {
        return new Endereco(
            source.getId(),
            source.getRua(),
            source.getBairro(),
            source.getCep(),
            source.getNumero(),
            cidade,
            usuario,
            source.getCreatedAt(),
            source.getUpdatedAt()
        );
    }

}
