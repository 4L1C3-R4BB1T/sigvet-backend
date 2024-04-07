package br.com.sigvet.api.usecase.base;

import br.com.sigvet.api.application.exception.UsuarioExistsException;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface ICadastrarUseCase<T> {
    T executar(T type) throws DomainInvalidException, UsuarioExistsException;
}
