package br.com.sigvet.sigvetapi.feature.vaccine;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import br.com.sigvet.sigvetapi.common.EntityMapper;
import br.com.sigvet.sigvetapi.common.entities.VaccineEntity;

@Mapper
public interface VaccineMapper extends EntityMapper<VaccineRequestDTO, VaccineEntity> {

    VaccineMapper INSTANCE = Mappers.getMapper(VaccineMapper.class);

    @Mappings({
        @Mapping(target = "deleted", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "id", ignore = true),
    })
    VaccineEntity fromModel(VaccineRequestDTO source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void map(@MappingTarget VaccineEntity target, VaccineEntity source);

}
