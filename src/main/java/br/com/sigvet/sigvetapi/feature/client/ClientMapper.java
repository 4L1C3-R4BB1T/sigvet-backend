package br.com.sigvet.sigvetapi.feature.client;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import br.com.sigvet.sigvetapi.common.EntityMapper;
import br.com.sigvet.sigvetapi.common.entities.AddressEntity;
import br.com.sigvet.sigvetapi.common.entities.CityEntity;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;

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

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void map(@MappingTarget ClientEntity target, ClientEntity source);

    default AddressEntity mapAddress(ClientRequestDTO source) {
        final var address = source.getAddress();
        return AddressEntity.builder()
            .street(address.getStreet())
            .neighborhood(address.getNeighborhood())
            .number(address.getNumber())
            .zipCode(address.getZipCode())
            .city(CityEntity.builder().id(address.getCityId()).build()
        ).build();
    }

}
