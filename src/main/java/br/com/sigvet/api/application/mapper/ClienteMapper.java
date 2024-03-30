package br.com.sigvet.api.application.mapper;


import org.springframework.stereotype.Component;

import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.domain.entities.Documento;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.infrastructure.entity.ClienteEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClienteMapper {

    private final AnimalMapper animalMapper;

    private final EnderecoMapper enderecoMapper;

    public ClienteEntity toEntity(Cliente source) {

        return ClienteEntity.builder()
            .animais(source.getAnimais().stream().map(animalMapper::toEntity).toList())
            .cpf(source.getCpf().getValor())
            .createdAt(source.getCreatedAt())
            .updatedAt(source.getUpdatedAt())
            .email(source.getEmail())
            .endereco(enderecoMapper.toEntity(source.getEndereco()))        
            .id(source.getId())
            .nome(source.getNome())
            .senha(source.getSenha())
            .telefone(source.getTelefone())
            .usuario(source.getUsuario())
            .build();
    }

    public Cliente toCliente(ClienteEntity clienteSaved) throws DomainInvalidException {
        return new Cliente(
            clienteSaved.getId(),
            clienteSaved.getUsuario(),
            clienteSaved.getSenha(),
            clienteSaved.getEmail(),
            clienteSaved.getNome(),
            new Documento(clienteSaved.getCpf()),
            clienteSaved.getTelefone(),
            enderecoMapper.toEndereco(clienteSaved.getEndereco())
        );
    }

}
