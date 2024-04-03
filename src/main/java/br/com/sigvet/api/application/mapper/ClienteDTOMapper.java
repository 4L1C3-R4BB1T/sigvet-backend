package br.com.sigvet.api.application.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import br.com.sigvet.api.application.dto.ClienteDTO;
import br.com.sigvet.api.core.domain.entities.Cliente;

@Mapper
public interface ClienteDTOMapper {
    
    ClienteDTOMapper MAPPER = Mappers.getMapper(ClienteDTOMapper.class);
    
    @Mappings({
        @Mapping(target = "cpf", source="cpf.valor"),
        @Mapping(target = "endereco.cidade.estado", source = "endereco.cidade.uf.sigla")
        
    })
    ClienteDTO toClienteDTO(Cliente source);

    List<ClienteDTO> toClienteDTO(List<Cliente> source);
}
