package br.com.sigvet.sigvetapi.feature.consult;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import br.com.sigvet.sigvetapi.common.EntityMapper;
import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import br.com.sigvet.sigvetapi.common.entities.ConsultEntity;
import br.com.sigvet.sigvetapi.common.entities.VeterinarianEntity;

@Mapper
public interface ConsultMapper extends EntityMapper<ConsultRequestDTO, ConsultEntity> {

    ConsultMapper INSTANCE = Mappers.getMapper(ConsultMapper.class);

    @Mappings({
        @Mapping(target = "deleted", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "animal", expression = "java(mapAnimalEntity(source))"),
        @Mapping(target = "veterinarian", expression = "java(mapVeterinarianEntity(source))")
    })
    ConsultEntity fromModel(ConsultRequestDTO source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void map(@MappingTarget ConsultEntity target, ConsultEntity source);

    default VeterinarianEntity mapVeterinarianEntity(ConsultRequestDTO source) {
        return VeterinarianEntity.builder().id(source.veterinarianId()).build();
    }
    
    default AnimalEntity mapAnimalEntity(ConsultRequestDTO source) {
        return AnimalEntity.builder().id(source.animalId()).build();
    }

}
