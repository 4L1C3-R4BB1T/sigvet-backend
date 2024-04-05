package br.com.sigvet.api.application.mapper.vacina;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.sigvet.api.application.dto.vacina.VacinaDTO;
import br.com.sigvet.api.core.domain.entities.Vacina;

@Mapper
public interface VacinaDTOMapper {
    
    VacinaDTOMapper MAPPER = Mappers.getMapper(VacinaDTOMapper.class);

    VacinaDTO toVacinaDTO(Vacina source);

    List<VacinaDTO> toVacinaDTO(List<Vacina> source);
}
