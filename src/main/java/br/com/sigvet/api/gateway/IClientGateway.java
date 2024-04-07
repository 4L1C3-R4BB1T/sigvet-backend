package br.com.sigvet.api.gateway;

import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.gateway.base.IBaseGateway;
import br.com.sigvet.api.infrastructure.entity.ClienteEntity;

public interface IClientGateway extends IBaseGateway<Cliente, ClienteEntity> {
}
