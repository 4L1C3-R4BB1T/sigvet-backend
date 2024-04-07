package br.com.sigvet.api.usecase.base;

import br.com.sigvet.api.application.exception.UsuarioExistsException;
import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IAtualizarUseCase<T> {
    T executar(Long id, T type) throws UsuarioExistsException, DomainInvalidException, UsuarioNotFoundException;
}
