package br.com.sigvet.api.usecase.cliente;

import br.com.sigvet.api.application.exception.ClienteNaoEncontradoException;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IObterClientePorIdUseCase {
    Cliente executar(Long id) throws DomainInvalidException, ClienteNaoEncontradoException;
}
