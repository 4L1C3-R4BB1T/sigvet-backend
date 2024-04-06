package br.com.sigvet.api.application.mapper.veterinario;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import br.com.sigvet.api.application.dto.veterinario.ResponseVeterinarioDTO;
import br.com.sigvet.api.core.domain.entities.Veterinario;
@Mapper
public interface VeterinarioDTOMapper {

    VeterinarioDTOMapper MAPPER = Mappers.getMapper(VeterinarioDTOMapper.class);


     @Mappings({
        @Mapping(target = "cpf", source = "cpf.valor"),
        @Mapping(target = "endereco.cidade", source = "endereco.cidade.nome"),
        @Mapping(target = "endereco.estado", source = "endereco.cidade.uf.sigla"),
        @Mapping(target = "crmv", expression = "java(mapToCrmvFull(source.getCrmvUf(), source.getCrmv()))")
        
    })
    ResponseVeterinarioDTO toVeterinarioDTO(Veterinario source);

    List<ResponseVeterinarioDTO> toVeterinarioDTO(List<Veterinario> source);

    default String mapToCrmvFull(String uf, String valor) {
        return "%s-%s".formatted(uf, valor);
    }
}
