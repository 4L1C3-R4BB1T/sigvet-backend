package br.com.sigvet.api.application.mapper.cliente;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.sigvet.api.application.dto.cliente.AtualizarClienteDTO;
import br.com.sigvet.api.application.dto.cliente.CriarClienteDTO;
import br.com.sigvet.api.application.exception.CidadeNaoExistenteException;
import br.com.sigvet.api.application.mapper.AnimalMapper;
import br.com.sigvet.api.application.mapper.CidadeMapper;
import br.com.sigvet.api.application.mapper.EnderecoMapper;
import br.com.sigvet.api.application.mapper.base.IClienteMapper;
import br.com.sigvet.api.core.domain.entities.Animal;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.domain.entities.Documento;
import br.com.sigvet.api.core.domain.entities.Endereco;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.infrastructure.entity.ClienteEntity;
import br.com.sigvet.api.infrastructure.repository.CidadeJpaRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public final class ClienteMapper implements IClienteMapper {

    private final AnimalMapper animalMapper;

    private final EnderecoMapper enderecoMapper;

    private final CidadeMapper cidadeMapper;

    private final CidadeJpaRepository cidadeJpaRepository;

    public ClienteEntity fromDomainToEntity(Cliente source) {
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
            .deleted(false) //TODO validar isso no futuro, talvez altere o valor já setado no banco
            .build();

        clienteEntity.setEndereco(enderecoMapper.toEntity(source.getEndereco(), clienteEntity, cidadeMapper.toEntity(source.getEndereco().getCidade())));
        clienteEntity.setAnimais(source.getAnimais().stream().map(animal -> animalMapper.toEntity(animal, clienteEntity)).toList());

        return clienteEntity;
    }

    public Cliente fromEntityToDomain(ClienteEntity source) throws DomainInvalidException {
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
    
    public Cliente fromCriarModelToDomain(CriarClienteDTO source) throws DomainInvalidException, CidadeNaoExistenteException {

        var cidadeEntity = cidadeJpaRepository.findByNomeAndSiglaUf(source.cidade(), source.uf());

        if (cidadeEntity.isEmpty()) {
            throw new CidadeNaoExistenteException("Cidade não encontrada");
        }

        var cidade = cidadeMapper.toCidade(cidadeEntity.get());

        return new Cliente(
            source.usuario(),
            source.senha(),
            source.email(),
            source.nome(),
            new Documento(source.cpf()),
            source.telefone(),
            new Endereco(source.rua(), source.bairro(), source.cep(), source.numero(), cidade)
        );
    }

    public Cliente fromAtualizarModelToDomain(AtualizarClienteDTO source) throws DomainInvalidException {

        var cidadeEntity = cidadeJpaRepository.findByNomeAndSiglaUf(source.cidade(), source.uf());

        if (cidadeEntity.isEmpty()) {
            throw new CidadeNaoExistenteException("Cidade não encontrada");
        }

        var cidade = cidadeMapper.toCidade(cidadeEntity.get());
        
        return new Cliente(
            source.usuario(),
            source.senha(),
            source.email(),
            source.nome(),
            new Documento(source.cpf()),
            source.telefone(),
            new Endereco(source.rua(), source.bairro(), source.cep(), source.numero(), cidade)
        );
    }


}
