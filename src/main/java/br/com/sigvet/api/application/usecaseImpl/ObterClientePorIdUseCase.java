package br.com.sigvet.api.application.usecaseImpl;

import br.com.sigvet.api.application.exception.ClienteNaoEncontradoException;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IClienteGateway;
import br.com.sigvet.api.usecase.cliente.IObterClientePorIdUseCase;

public class ObterClientePorIdUseCase implements IObterClientePorIdUseCase {

    private final IClienteGateway clienteGateway;

    public ObterClientePorIdUseCase(IClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public Cliente executar(Long id) throws DomainInvalidException, ClienteNaoEncontradoException {
        return clienteGateway.findById(id);
    }
    
}
