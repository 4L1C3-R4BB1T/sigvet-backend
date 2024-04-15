package br.com.sigvet.api.application.mapper.cliente;

import java.util.List;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import br.com.sigvet.api.application.dto.cliente.ClientResponseDTO;
import br.com.sigvet.api.application.service.base.IClienteUploadPhotoService;
import br.com.sigvet.api.core.domain.entities.Cliente;

@Mapper
public interface ClienteDTOMapper {

    
    ClienteDTOMapper MAPPER = Mappers.getMapper(ClienteDTOMapper.class );
    
    @Mappings({
       @Mapping(target = "cpf", source="cpf.valor"),
       @Mapping(target = "endereco.cidade", source = "endereco.cidade.nome"),
       @Mapping(target = "endereco.estado", source = "endereco.cidade.uf.sigla"), 
       @Mapping(target = "foto", ignore = true)
    })
    ClientResponseDTO toClienteDTO(Cliente source);

    List<ClientResponseDTO> toClienteDTO(List<Cliente> source); 
    
    @Mappings({
        @Mapping(target = "cpf", source="cpf.valor"),
        @Mapping(target = "endereco.cidade", source = "endereco.cidade.nome"),
        @Mapping(target = "endereco.estado", source = "endereco.cidade.uf.sigla"), 
        @Mapping(target = "foto", expression = "java(uploadPhoto(source.getId(), service))")
     })
     ClientResponseDTO toClienteDTO(Cliente source, @Context IClienteUploadPhotoService service);

    default String uploadPhoto(Long id, IClienteUploadPhotoService service) {
        try {
            service.getPhoto(id);
            return "/api/customer/%d/photo".formatted(id);
        } catch (Exception ex) {
            return null;
        }
    }
}
