package  br.com.sigvet.api.application.usecase.impl.cliente;

import org.springframework.data.domain.Page;

import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IClientGateway;
import br.com.sigvet.api.usecase.base.IListarUseCase;

public class ListarClientesUseCase implements IListarUseCase<Cliente> {

    private final IClientGateway clienteGateway;

    public ListarClientesUseCase(IClientGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public Page<Cliente> executar(FilterModel filter) throws DomainInvalidException {
        return clienteGateway.findAll(filter);
    }
    
}
