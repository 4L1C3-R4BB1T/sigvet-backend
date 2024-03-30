package br.com.sigvet.api.application.mapper;

import org.springframework.stereotype.Component;

import br.com.sigvet.api.core.domain.entities.Animal;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.infrastructure.entity.AnimalEntity;
import br.com.sigvet.api.infrastructure.entity.ClienteEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AnimalMapper {

    public AnimalEntity toEntity(Animal source, ClienteEntity cliente) {
        return AnimalEntity.builder()   
        .id(source.getId())
        .cliente(cliente)
        .dataNascimento(source.getDataNascimento())
        .nome(source.getNome())
        .raca(source.getRaca())
        .updatedAt(source.getUpdatedAt())
        .createdAt(source.getCreatedAt())
        .build();
    }

    public Animal toAnimal(AnimalEntity source, Cliente cliente) throws DomainInvalidException {
        return new Animal(
            source.getId(),
            source.getNome(),
            source.getRaca(),
            source.getDataNascimento(),
            source.getCreatedAt(),
            source.getUpdatedAt(),
            cliente
        );
    }

}
