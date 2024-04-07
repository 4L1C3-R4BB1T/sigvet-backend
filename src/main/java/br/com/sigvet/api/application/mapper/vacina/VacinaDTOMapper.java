package br.com.sigvet.api.application.mapper.vacina;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.sigvet.api.application.dto.vacina.VaccineResponseDTO;
import br.com.sigvet.api.core.domain.entities.Vacina;

@Mapper
public interface VacinaDTOMapper {
    
    VacinaDTOMapper MAPPER = Mappers.getMapper(VacinaDTOMapper.class );

    VaccineResponseDTO toVacinaDTO(Vacina source);

    List<VaccineResponseDTO> toVacinaDTO(List<Vacina> source);
}
