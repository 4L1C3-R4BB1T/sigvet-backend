package br.com.sigvet.api.usecase.vacina;

import br.com.sigvet.api.application.exception.VacinaNaoEncontradaException;
import br.com.sigvet.api.core.domain.entities.Vacina;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IAtualizarVacinaUseCase {
    Vacina executar(Long id, Vacina vacina) throws VacinaNaoEncontradaException, DomainInvalidException;
}
