package br.com.sigvet.api.application.mapper.veterinario;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import br.com.sigvet.api.application.dto.veterinario.VeterinarioDTO;
import br.com.sigvet.api.core.domain.entities.Veterinario;
@Mapper
public interface VeterinarioDTOMapper {

    VeterinarioDTOMapper MAPPER = Mappers.getMapper(VeterinarioDTOMapper.class);

     @Mappings({
        @Mapping(target = "cpf", source="cpf.valor"),
        @Mapping(target = "endereco.cidade.estado", source = "endereco.cidade.uf.sigla")
        
    })
    VeterinarioDTO toVeterinarioDTO(Veterinario source);

    List<VeterinarioDTO> toVeterinarioDTO(List<Veterinario> source);
    
}
