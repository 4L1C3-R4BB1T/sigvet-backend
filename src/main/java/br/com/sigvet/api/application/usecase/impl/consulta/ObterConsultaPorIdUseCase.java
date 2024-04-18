package br.com.sigvet.api.application.usecase.impl.consulta;

import br.com.sigvet.api.core.domain.entities.Consulta;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.usecase.base.IObterPorIdUseCase;

public class ObterConsultaPorIdUseCase implements IObterPorIdUseCase<Consulta> {

    @Override
    public Consulta executar(Long id) throws DomainInvalidException {
        return null;
    }

}
