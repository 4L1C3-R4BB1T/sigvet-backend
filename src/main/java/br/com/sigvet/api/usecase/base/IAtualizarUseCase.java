package br.com.sigvet.api.usecase.base;

import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IAtualizarUseCase<T> {
    T executar(Long id, T type) throws UsuarioExistenteException, DomainInvalidException, UsuarioNaoEncontradoException;
}
