package br.com.sigvet.api.application.usecaseImpl.cliente;

import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.application.gatewayImpl.ClienteGateway;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.usecase.cliente.ICadastrarClienteUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarClienteUseCase implements ICadastrarClienteUseCase {

    private final ClienteGateway clienteGateway;

    @Override
    public Cliente executar(Cliente cliente) throws DomainInvalidException, UsuarioExistenteException {
        return clienteGateway.save(cliente);
    }
    
}
