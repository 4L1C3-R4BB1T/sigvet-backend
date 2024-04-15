package br.com.sigvet.api.gateway;

import br.com.sigvet.api.core.domain.entities.Vacinacao;
import br.com.sigvet.api.gateway.base.IBaseGateway;
import br.com.sigvet.api.infrastructure.entity.VacinacaoEntity;

public interface IVacinacaoGateway extends IBaseGateway<Vacinacao, VacinacaoEntity> {
    
}
