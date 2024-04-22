package br.com.sigvet.sigvetapi.feature.vaccination;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import br.com.sigvet.sigvetapi.common.EntityMapper;
import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import br.com.sigvet.sigvetapi.common.entities.VaccinationEntity;
import br.com.sigvet.sigvetapi.common.entities.VaccineEntity;
import br.com.sigvet.sigvetapi.common.entities.VeterinarianEntity;

@Mapper
public interface VaccinationMapper extends EntityMapper<VaccinationRequestDTO, VaccinationEntity> {

    VaccinationMapper INSTANCE = Mappers.getMapper(VaccinationMapper.class);

    @Mappings({
        @Mapping(target = "deleted", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "veterinarian", expression = "java(mapVeterinarianEntity(source))"),
        @Mapping(target = "vaccine", expression = "java(mapVaccineEntity(source))"),
        @Mapping(target = "animal", expression = "java(mapAnimalEntity(source))"),
    })
    VaccinationEntity fromModel(VaccinationRequestDTO source);

    
    default VaccineEntity mapVaccineEntity(VaccinationRequestDTO source) {
        return VaccineEntity.builder().id(source.vaccineId()).build();
    }
    
    default VeterinarianEntity mapVeterinarianEntity(VaccinationRequestDTO source) {
        return VeterinarianEntity.builder().id(source.veterinarianId()).build();
    }
    
    default AnimalEntity mapAnimalEntity(VaccinationRequestDTO source) {
        return AnimalEntity.builder().id(source.animalId()).build();
    }

}
