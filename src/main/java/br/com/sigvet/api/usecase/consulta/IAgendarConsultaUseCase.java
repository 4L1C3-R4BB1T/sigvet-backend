package br.com.sigvet.api.usecase.consulta;

import br.com.sigvet.api.core.domain.entities.Consulta;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IAgendarConsultaUseCase {
    Consulta executar(Consulta consulta) throws DomainInvalidException;
}
