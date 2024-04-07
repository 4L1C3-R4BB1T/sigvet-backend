package  br.com.sigvet.api.application.usecase.impl.cliente;

import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IClientGateway;
import br.com.sigvet.api.usecase.base.IObterPorIdUseCase;

public class ObterClientePorIdUseCase implements IObterPorIdUseCase<Cliente> {

    private final IClientGateway clienteGateway;

    public ObterClientePorIdUseCase(IClientGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public Cliente executar(Long id) throws DomainInvalidException, UsuarioNotFoundException {
        return clienteGateway.findById(id);
    }
    
}
