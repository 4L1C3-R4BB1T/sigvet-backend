package br.com.sigvet.sigvetapi.feature.photo;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.sigvet.sigvetapi.common.entities.PhotoEntity;

@Mapper
public interface PhotoMapper {
    
    PhotoMapper INSTANCE = Mappers.getMapper( PhotoMapper.class );

    @Mapping(target = "link", ignore = true)
    PhotoResponseDTO map(PhotoEntity source);
}
