package br.com.sigvet.api.application.usecaseImpl.cliente;

import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IClienteGateway;
import br.com.sigvet.api.usecase.base.IAtualizarUseCase;

public class AtualizarClienteUseCase implements IAtualizarUseCase<Cliente> {

    private final IClienteGateway clienteGateway;

    public AtualizarClienteUseCase(IClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public Cliente executar(Long id, Cliente cliente) throws UsuarioNaoEncontradoException, UsuarioExistenteException, DomainInvalidException {
        return clienteGateway.update(id, cliente);
    }
    
}
