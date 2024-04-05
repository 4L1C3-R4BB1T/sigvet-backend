package br.com.sigvet.api.application.usecaseImpl.cliente;

import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IClienteGateway;
import br.com.sigvet.api.usecase.base.IObterPorIdUseCase;

public class ObterClientePorIdUseCase implements IObterPorIdUseCase<Cliente> {

    private final IClienteGateway clienteGateway;

    public ObterClientePorIdUseCase(IClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public Cliente executar(Long id) throws DomainInvalidException, UsuarioNaoEncontradoException {
        return clienteGateway.findById(id);
    }
    
}
