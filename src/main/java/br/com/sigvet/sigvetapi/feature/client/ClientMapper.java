package br.com.sigvet.sigvetapi.feature.client;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import br.com.sigvet.sigvetapi.common.EntityMapper;
import br.com.sigvet.sigvetapi.common.entities.AddressEntity;
import br.com.sigvet.sigvetapi.common.entities.CityEntity;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import br.com.sigvet.sigvetapi.common.entities.StateEntity;

@Mapper
public interface ClientMapper extends EntityMapper<ClientRequestDTO, ClientEntity> {

    ClientMapper INSTANCE = Mappers.getMapper( ClientMapper.class );

    @Mappings({
        @Mapping(target = "deleted", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "animals", ignore = true),
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "address", expression = "java(mapAddress(source))")
    })
    ClientEntity fromModel(ClientRequestDTO source);

    void map(@MappingTarget ClientEntity target, ClientEntity source);

    default AddressEntity mapAddress(ClientRequestDTO source) {
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
