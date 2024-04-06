package  br.com.sigvet.api.application.usecase.impl.cliente;

import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.application.gateway.impl.ClienteGateway;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.usecase.base.ICadastrarUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CadastrarClienteUseCase implements ICadastrarUseCase<Cliente> {

    private final ClienteGateway clienteGateway;

    @Override
    public Cliente executar(Cliente cliente) throws DomainInvalidException, UsuarioExistenteException {
        return clienteGateway.save(cliente);
    }
    
}
