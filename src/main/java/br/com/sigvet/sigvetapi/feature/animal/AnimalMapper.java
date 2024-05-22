package br.com.sigvet.sigvetapi.feature.animal;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import br.com.sigvet.sigvetapi.common.EntityMapper;
import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;

@Mapper
public interface AnimalMapper extends EntityMapper<AnimalRequestDTO, AnimalEntity> {

    AnimalMapper INSTANCE = Mappers.getMapper( AnimalMapper.class );
    
    @Mappings({
        @Mapping(target = "deleted", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "photoUrl", ignore = true),
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "client", expression = "java(mapClientEntity(source))"),
    })
    AnimalEntity fromModel(AnimalRequestDTO source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void map(@MappingTarget AnimalEntity target, AnimalEntity source);

    default ClientEntity mapClientEntity(AnimalRequestDTO source) {
        return ClientEntity.builder().id(source.clientId()).build();
    }
    
}
