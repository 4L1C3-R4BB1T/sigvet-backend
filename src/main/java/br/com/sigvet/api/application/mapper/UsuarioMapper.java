package br.com.sigvet.api.application.mapper;

import org.springframework.stereotype.Component;

import br.com.sigvet.api.core.domain.entities.Documento;
import br.com.sigvet.api.core.domain.entities.Usuario;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.infrastructure.entity.UsuarioEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UsuarioMapper {

    private final EnderecoMapper enderecoMapper;

    public UsuarioEntity toEntity(Usuario source) {
        return UsuarioEntity.builder()
            .cpf(source.getCpf().getValor())
            .updatedAt(source.getUpdatedAt())
            .createdAt(source.getCreatedAt())
            .email(source.getEmail())
            .endereco(enderecoMapper.toEntity(source.getEndereco()))
            .id(source.getId())
            .nome(source.getNome())
            .senha(source.getSenha())
            .telefone(source.getTelefone())
            .usuario(source.getUsuario())
            .build();
    }

    public Usuario toUsuario(UsuarioEntity source) throws DomainInvalidException {
        return new Usuario(
            source.getId(),
            source.getUsuario(), 
            source.getSenha(),
            source.getEmail(), 
            source.getNome(), 
            new Documento(source.getCpf()), 
            source.getTelefone(), 
            enderecoMapper.toEndereco(source.getEndereco()));
    }

}
