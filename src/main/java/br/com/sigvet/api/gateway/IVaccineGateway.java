package br.com.sigvet.api.gateway;

import br.com.sigvet.api.core.domain.entities.Vacina;
import br.com.sigvet.api.gateway.base.IBaseGateway;
import br.com.sigvet.api.infrastructure.entity.VacinaEntity;

public interface IVaccineGateway extends IBaseGateway<Vacina, VacinaEntity> {
}
