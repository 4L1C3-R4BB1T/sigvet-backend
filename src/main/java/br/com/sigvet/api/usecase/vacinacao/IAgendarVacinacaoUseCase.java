package br.com.sigvet.api.usecase.vacinacao;

import br.com.sigvet.api.core.domain.entities.Vacinacao;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IAgendarVacinacaoUseCase {
    Vacinacao executar(Vacinacao vacinacao) throws DomainInvalidException;
}
