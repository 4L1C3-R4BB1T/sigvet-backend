package br.com.sigvet.sigvetapi.feature.diagnostic;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import br.com.sigvet.sigvetapi.common.EntityMapper;
import br.com.sigvet.sigvetapi.common.entities.ConsultEntity;
import br.com.sigvet.sigvetapi.common.entities.DiagnosticEntity;

@Mapper
public interface DiagnosticMapper extends EntityMapper<DiagnosticRequestDTO, DiagnosticEntity> {

    DiagnosticMapper INSTANCE = Mappers.getMapper(DiagnosticMapper.class);

    @Mappings({
        @Mapping(target = "deleted", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "consult", expression = "java(mapConsultEntity(source))"),
    })
    DiagnosticEntity fromModel(DiagnosticRequestDTO source);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void map(@MappingTarget DiagnosticEntity target, DiagnosticEntity source);

    default ConsultEntity mapConsultEntity(DiagnosticRequestDTO source) {
        return ConsultEntity.builder().id(source.consultId()).build();
    }

}
