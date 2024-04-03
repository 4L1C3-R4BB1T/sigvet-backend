package br.com.sigvet.api.usecase.cliente;

import br.com.sigvet.api.application.exception.ClienteNaoEncontradoException;
import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IAtualizarClienteUseCase {
    Cliente executar(Long id, Cliente cliente) throws ClienteNaoEncontradoException, UsuarioExistenteException, DomainInvalidException;
}
