package br.com.sigvet.api.gateway;

import org.springframework.data.jpa.domain.Specification;

import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.infrastructure.entity.ClienteEntity;

public interface IClienteGateway extends IBaseGateway<Cliente> {
    
    Specification<ClienteEntity> buildSpecification(FilterModel filterModel);
}
