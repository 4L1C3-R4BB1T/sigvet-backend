package br.com.sigvet.api.application.mapper.base;

import br.com.sigvet.api.application.dto.vacinacao.AgendarVacinacaoRequestDTO;
import br.com.sigvet.api.core.domain.entities.Vacinacao;
import br.com.sigvet.api.infrastructure.entity.VacinacaoEntity;

// Coloquei Object pra burlar 
public interface IVacinacaoMapper extends IBaseMapper<Vacinacao, VacinacaoEntity, AgendarVacinacaoRequestDTO, Object> {
    
}
