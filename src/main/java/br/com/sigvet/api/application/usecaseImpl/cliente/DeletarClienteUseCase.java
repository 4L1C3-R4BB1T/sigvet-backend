package br.com.sigvet.api.application.usecaseImpl.cliente;

import br.com.sigvet.api.application.exception.ClienteNaoEncontradoException;
import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IClienteGateway;
import br.com.sigvet.api.usecase.cliente.IDeletarClienteUseCase;

public class DeletarClienteUseCase implements IDeletarClienteUseCase {

    private final IClienteGateway clienteGateway;

    public DeletarClienteUseCase(IClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public boolean executar(Long id) throws UsuarioExistenteException, DomainInvalidException, ClienteNaoEncontradoException {
        clienteGateway.findById(id);
        return clienteGateway.delete(id);
    }
    
}
