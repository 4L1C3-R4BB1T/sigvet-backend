package  br.com.sigvet.api.application.usecase.impl.cliente;

import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.application.exception.UsuarioExistsException;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IClientGateway;
import br.com.sigvet.api.usecase.base.IDeletarUseCase;

public class DeletarClienteUseCase implements IDeletarUseCase<Cliente> {

    private final IClientGateway clienteGateway;

    public DeletarClienteUseCase(IClientGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public boolean executar(Long id) throws UsuarioExistsException, DomainInvalidException, UsuarioNotFoundException {
        clienteGateway.findById(id);
        return clienteGateway.delete(id);
    }
    
}
