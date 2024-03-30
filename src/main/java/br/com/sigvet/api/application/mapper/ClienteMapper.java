package br.com.sigvet.api.application.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.sigvet.api.core.domain.entities.Animal;
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

    private final CidadeMapper cidadeMapper;

    public ClienteEntity toEntity(Cliente source) {
        var clienteEntity =  ClienteEntity.builder()
            .cpf(source.getCpf().getValor())
            .createdAt(source.getCreatedAt())
            .updatedAt(source.getUpdatedAt())
            .email(source.getEmail())       
            .id(source.getId())
            .nome(source.getNome())
            .senha(source.getSenha())
            .telefone(source.getTelefone())
            .usuario(source.getUsuario())
            .build();

        clienteEntity.setEndereco(enderecoMapper.toEntity(source.getEndereco(), clienteEntity, cidadeMapper.toEntity(source.getEndereco().getCidade())));
        clienteEntity.setAnimais(source.getAnimais().stream().map(animal -> animalMapper.toEntity(animal, clienteEntity)).toList());

        return clienteEntity;
    }

    public Cliente toCliente(ClienteEntity source) throws DomainInvalidException {
        var cliente = new Cliente(
            source.getId(), 
            source.getUsuario(), 
            source.getSenha(), 
            source.getEmail(),
             source.getNome(), 
             new Documento(source.getCpf()),
             source.getTelefone(),
             source.getCreatedAt(),
             source.getUpdatedAt()
        );


        cliente.setEndereco(enderecoMapper.toEndereco(source.getEndereco(), cliente, cidadeMapper.toCidade(source.getEndereco().getCidade())));

        List<Animal> animais = new ArrayList<>();

        for (var animalEntity: source.getAnimais()) {
            animais.add(animalMapper.toAnimal(animalEntity, cliente));
        }

       cliente.setAnimais(animais);

       return cliente;
    }


}
