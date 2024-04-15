package br.com.sigvet.api.application.mapper.vacinacao;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.sigvet.api.application.dto.vacinacao.VacinacaoResponseDTO;
import br.com.sigvet.api.application.mapper.animal.AnimalDTOMapper;
import br.com.sigvet.api.application.mapper.veterinario.VeterinarioDTOMapper;
import br.com.sigvet.api.core.domain.entities.Vacinacao;

@Mapper(uses = { VeterinarioDTOMapper.class, AnimalDTOMapper.class } )
public interface VacinacaoDTOMapper {
    
    VacinacaoDTOMapper MAPPER = Mappers.getMapper(VacinacaoDTOMapper.class);

    VacinacaoResponseDTO toVacinacaoResponseDTO(Vacinacao source);

    List<VacinacaoResponseDTO> toVacinacaoResponseDTO(List<Vacinacao> source);
}
