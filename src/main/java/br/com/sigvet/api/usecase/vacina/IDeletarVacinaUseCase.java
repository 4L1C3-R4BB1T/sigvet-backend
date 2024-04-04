package br.com.sigvet.api.usecase.vacina;

import br.com.sigvet.api.application.exception.VacinaNaoEncontradaException;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IDeletarVacinaUseCase {
    boolean executar(Long id) throws DomainInvalidException, VacinaNaoEncontradaException;
}
