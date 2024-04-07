package br.com.sigvet.api.application.mapper.vacina;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.sigvet.api.application.dto.vacina.ResponseVacinaDTO;
import br.com.sigvet.api.core.domain.entities.Vacina;

@Mapper
public interface VacinaDTOMapper {
    
    VacinaDTOMapper MAPPER = Mappers.getMapper(VacinaDTOMapper.class );

    ResponseVacinaDTO toVacinaDTO(Vacina source);

    List<ResponseVacinaDTO> toVacinaDTO(List<Vacina> source);
}
