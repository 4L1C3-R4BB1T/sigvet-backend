package br.com.sigvet.api.usecase.base;

import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IObterPorIdUseCase<T> {
    T executar(Long id) throws DomainInvalidException, UsuarioNotFoundException;
}
