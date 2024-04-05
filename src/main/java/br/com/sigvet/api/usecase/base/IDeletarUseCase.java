package br.com.sigvet.api.usecase.base;

import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IDeletarUseCase<T> {
    boolean executar(Long id) throws UsuarioExistenteException, DomainInvalidException, UsuarioNaoEncontradoException;
}
