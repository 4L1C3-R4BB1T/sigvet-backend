package br.com.sigvet.api.application.mapper.animal;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import br.com.sigvet.api.application.dto.animal.AnimalResponseDTO;
import br.com.sigvet.api.core.domain.entities.Animal;

@Mapper
public interface AnimalDTOMapper {
    
    AnimalDTOMapper MAPPER = Mappers.getMapper( AnimalDTOMapper.class );

    @Mappings({
        @Mapping(target = "cliente.cpf", source = "cliente.cpf.valor"),
        @Mapping(target = "cliente.endereco.cidade", source = "cliente.endereco.cidade.nome"),
        @Mapping(target = "cliente.endereco.estado", source = "cliente.endereco.cidade.uf.sigla")
    })
    AnimalResponseDTO toAnimalDTO(Animal source);

    List<AnimalResponseDTO> toAnimalDTO(List<Animal> source);
}
