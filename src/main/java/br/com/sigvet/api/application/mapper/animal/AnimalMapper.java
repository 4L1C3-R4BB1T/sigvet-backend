package br.com.sigvet.api.application.mapper.animal;

import java.util.Objects;

import org.springframework.stereotype.Component;

import br.com.sigvet.api.application.dto.animal.UpdateAnimalRequestDTO;
import br.com.sigvet.api.application.dto.animal.CreateAnimalRequestDTO;
import br.com.sigvet.api.application.exception.CidadeNotFoundException;
import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.application.mapper.base.IAnimalMapper;
import br.com.sigvet.api.application.mapper.base.IClienteMapper;
import br.com.sigvet.api.core.domain.entities.Animal;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.infrastructure.entity.AnimalEntity;
import br.com.sigvet.api.infrastructure.repository.ClienteJpaRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AnimalMapper implements IAnimalMapper {

    private final ClienteJpaRepository clienteJpaRepository;

    private final IClienteMapper clienteMapper;

    @Override
    public AnimalEntity fromDomainToEntity(Animal source) {
        return AnimalEntity.builder()
            .cliente(clienteMapper.fromDomainToEntity(source.getCliente()))
            .createdAt(source.getCreatedAt())
            .updatedAt(source.getUpdatedAt())
            .dataNascimento(source.getDataNascimento())
            .deleted(false)
            .id(source.getId())
            .nome(source.getNome())
            .build();
    }

    @Override
    public Animal fromEntityToDomain(AnimalEntity source) throws DomainInvalidException {
        return new Animal(source.getId(), source.getNome(), source.getRaca(), source.getDataNascimento(), source.getCreatedAt(), source.getUpdatedAt(), clienteMapper.fromEntityToDomain(source.getCliente()));
    }

    @Override
    public Animal fromCriarModelToDomain(CreateAnimalRequestDTO source)
            throws DomainInvalidException, CidadeNotFoundException {
        
        var clienteEntity = clienteJpaRepository.findClienteByIdAndNotDeleted(source.clienteId());

        if (Objects.isNull(clienteEntity)) {
            throw new UsuarioNotFoundException("Cliente não encontrado");
        }

        return new Animal(source.nome(), source.raca(), source.dataNascimento(), clienteMapper.fromEntityToDomain(clienteEntity));
    }

    @Override
    public Animal fromAtualizarModelToDomain(UpdateAnimalRequestDTO source)
            throws DomainInvalidException, CidadeNotFoundException {
        return new Animal(source.nome(), source.raca(), source.dataNascimento());
    }

    
}
