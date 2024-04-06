package  br.com.sigvet.api.application.usecase.impl.cliente;

import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IClienteGateway;
import br.com.sigvet.api.usecase.base.IDeletarUseCase;

public class DeletarClienteUseCase implements IDeletarUseCase<Cliente> {

    private final IClienteGateway clienteGateway;

    public DeletarClienteUseCase(IClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public boolean executar(Long id) throws UsuarioExistenteException, DomainInvalidException, UsuarioNaoEncontradoException {
        clienteGateway.findById(id);
        return clienteGateway.delete(id);
    }
    
}
