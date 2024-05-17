package br.com.sigvet.sigvetapi.feature.user;

import java.util.Objects;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import br.com.sigvet.sigvetapi.common.entities.AddressEntity;
import br.com.sigvet.sigvetapi.common.entities.CityEntity;
import br.com.sigvet.sigvetapi.common.entities.UserEntity;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    
    @Mappings({
        @Mapping(target = "deleted", ignore = true),
        @Mapping(target = "createdAt", ignore = true),
        @Mapping(target = "updatedAt", ignore = true),
        @Mapping(target = "id", ignore = true),
        @Mapping(target = "address", expression = "java(mapAddress(source))"),
        @Mapping(target = "roles", ignore = true)
    })
    UserEntity fromModel(UserRequestDTO source);

    default AddressEntity mapAddress(UserRequestDTO source) {
        if (Objects.isNull(source.getAddress())) {
            return null;
        }
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
