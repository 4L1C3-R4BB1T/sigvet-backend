package br.com.sigvet.api.application.mapper.base;

import br.com.sigvet.api.application.dto.vacina.UpdateVaccineRequestDTO;
import br.com.sigvet.api.application.dto.vacina.CreateVaccineRequestDTO;
import br.com.sigvet.api.core.domain.entities.Vacina;
import br.com.sigvet.api.infrastructure.entity.VacinaEntity;

public interface IVacinaMapper extends IBaseMapper<Vacina, VacinaEntity, CreateVaccineRequestDTO, UpdateVaccineRequestDTO> {

}
