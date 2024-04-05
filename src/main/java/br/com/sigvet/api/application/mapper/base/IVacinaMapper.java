package br.com.sigvet.api.application.mapper.base;

import br.com.sigvet.api.application.dto.vacina.AtualizarVacinaDTO;
import br.com.sigvet.api.application.dto.vacina.CriarVacinaDTO;
import br.com.sigvet.api.core.domain.entities.Vacina;
import br.com.sigvet.api.infrastructure.entity.VacinaEntity;

public interface IVacinaMapper extends IBaseMapper<Vacina, VacinaEntity, CriarVacinaDTO, AtualizarVacinaDTO> {

}
