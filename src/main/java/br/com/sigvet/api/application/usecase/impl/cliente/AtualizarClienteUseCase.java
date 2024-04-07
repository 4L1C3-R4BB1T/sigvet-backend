package br.com.sigvet.api.application.usecase.impl.cliente;

import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.application.exception.UsuarioExistsException;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IClientGateway;
import br.com.sigvet.api.usecase.base.IAtualizarUseCase;

public class AtualizarClienteUseCase implements IAtualizarUseCase<Cliente> {

    private final IClientGateway clienteGateway;

    public AtualizarClienteUseCase(IClientGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public Cliente executar(Long id, Cliente cliente) throws UsuarioNotFoundException, UsuarioExistsException, DomainInvalidException {
        return clienteGateway.update(id, cliente);
    }
    
}
