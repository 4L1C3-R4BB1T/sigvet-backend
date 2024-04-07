package br.com.sigvet.api.application.mapper.cliente;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import br.com.sigvet.api.application.dto.cliente.ResponseClienteDTO;
import br.com.sigvet.api.core.domain.entities.Cliente;

@Mapper
public interface ClienteDTOMapper {
    
    ClienteDTOMapper MAPPER = Mappers.getMapper(ClienteDTOMapper.class );
    
    @Mappings({
       @Mapping(target = "cpf", source="cpf.valor"),
       @Mapping(target = "endereco.cidade", source = "endereco.cidade.nome"),
       @Mapping(target = "endereco.estado", source = "endereco.cidade.uf.sigla"),
        
    })
    ResponseClienteDTO toClienteDTO(Cliente source);

    List<ResponseClienteDTO> toClienteDTO(List<Cliente> source);
}
