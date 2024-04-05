package br.com.sigvet.api.usecase.cliente;

import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IDeletarClienteUseCase {
    boolean executar(Long id) throws UsuarioExistenteException, DomainInvalidException, UsuarioNaoEncontradoException;
}
