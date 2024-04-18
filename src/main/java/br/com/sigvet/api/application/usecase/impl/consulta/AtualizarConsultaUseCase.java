package br.com.sigvet.api.application.usecase.impl.consulta;

import br.com.sigvet.api.core.domain.entities.Consulta;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.usecase.base.IAtualizarUseCase;

public class AtualizarConsultaUseCase implements IAtualizarUseCase<Consulta> {

    @Override
    public Consulta executar(Long id, Consulta type) throws DomainInvalidException {
        return null;
    }

}
