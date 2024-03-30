package br.com.sigvet.api.application.usecaseImpl;


import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.application.model.PageModel;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IClienteGateway;
import br.com.sigvet.api.usecase.cliente.IListarClientesUseCase;

public class ListarClientesUseCase implements IListarClientesUseCase {

    private final IClienteGateway clienteGateway;

    public ListarClientesUseCase(IClienteGateway clienteGateway) {
        this.clienteGateway = clienteGateway;
    }

    @Override
    public PageModel<Cliente> executar(FilterModel filter) throws DomainInvalidException {
        return clienteGateway.findAll(filter);
    }
    
}
