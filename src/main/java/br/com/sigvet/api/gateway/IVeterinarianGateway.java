package br.com.sigvet.api.gateway;

import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.gateway.base.IBaseGateway;
import br.com.sigvet.api.infrastructure.entity.VeterinarioEntity;

public interface IVeterinarianGateway extends IBaseGateway<Veterinario,VeterinarioEntity> {
}
