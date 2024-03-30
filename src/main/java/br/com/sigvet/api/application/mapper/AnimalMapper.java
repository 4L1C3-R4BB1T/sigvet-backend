package br.com.sigvet.api.application.mapper;

import org.springframework.stereotype.Component;

import br.com.sigvet.api.core.domain.entities.Animal;
import br.com.sigvet.api.infrastructure.entity.AnimalEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AnimalMapper {

    private final ClienteMapper clienteMapper;

    public AnimalEntity toEntity(Animal source) {
        return AnimalEntity.builder()   
        .id(source.getId())
        .cliente(clienteMapper.toEntity(source.getCliente()))
        .dataNascimento(source.getDataNascimento())
        .nome(source.getNome())
        .raca(source.getRaca())
        .updatedAt(source.getUpdatedAt())
        .createdAt(source.getCreatedAt())
        .build();
    }

}
