package br.com.sigvet.api.application.usecase.impl.veterinario;


import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVeterinarioGateway;
import br.com.sigvet.api.usecase.base.IObterPorIdUseCase;

public class ObterVeterinarioPorIdUseCase implements IObterPorIdUseCase<Veterinario> {

    private final IVeterinarioGateway veterinarioGateway;

    public ObterVeterinarioPorIdUseCase(IVeterinarioGateway veterinarioGateway) {
        this.veterinarioGateway = veterinarioGateway;
    }

    @Override
    public Veterinario executar(Long id) throws DomainInvalidException, UsuarioNaoEncontradoException {
        return veterinarioGateway.findById(id);
    }
}
