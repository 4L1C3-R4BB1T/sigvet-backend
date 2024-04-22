package br.com.sigvet.sigvetapi.feature.veterinarian;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import br.com.sigvet.sigvetapi.common.EntityMapper;
import br.com.sigvet.sigvetapi.common.entities.AddressEntity;
import br.com.sigvet.sigvetapi.common.entities.CityEntity;
import br.com.sigvet.sigvetapi.common.entities.StateEntity;
import br.com.sigvet.sigvetapi.common.entities.VeterinarianEntity;

@Mapper
public interface VeterinarianMapper extends EntityMapper<VeterinarianRequestDTO, VeterinarianEntity> {

    VeterinarianMapper INSTANCE = Mappers.getMapper( VeterinarianMapper.class );

   @Mappings({
        @Mapping(target = "deleted", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "address", expression = "java(mapAddress(source))")
    })
    VeterinarianEntity fromModel(VeterinarianRequestDTO source);

    void map(@MappingTarget VeterinarianEntity target, VeterinarianEntity source);


    default AddressEntity mapAddress(VeterinarianRequestDTO source) {
        final var address = source.getAddress();
        return AddressEntity.builder()
            .street(address.getStreet())
            .neighborhood(address.getNeighborhood())
            .number(address.getNumber())
            .zipCode(address.getZipCode())
            .city(CityEntity.builder()
                .name(address.getCity())
                .state(StateEntity.builder().id(address.getState()).build())
                .build()
        ).build();
        
    }

}
