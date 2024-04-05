package br.com.sigvet.api.application.usecaseImpl.veterinario;

import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVeterinarioGateway;
import br.com.sigvet.api.usecase.veterinario.IObterVeterinarioPorIdUseCase;

public class ObterVeterinarioPorIdUseCase implements IObterVeterinarioPorIdUseCase {

    private final IVeterinarioGateway veterinarioGateway;

    public ObterVeterinarioPorIdUseCase(IVeterinarioGateway veterinarioGateway) {
        this.veterinarioGateway = veterinarioGateway;
    }

    @Override
    public Veterinario executar(Long id) throws DomainInvalidException, UsuarioNaoEncontradoException {
        return veterinarioGateway.findById(id);
    }
}
