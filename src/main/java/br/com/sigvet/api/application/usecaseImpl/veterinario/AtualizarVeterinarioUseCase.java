package br.com.sigvet.api.application.usecaseImpl.veterinario;

import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVeterinarioGateway;
import br.com.sigvet.api.usecase.veterinario.IAtualizarVeterinarioUseCase;

public class AtualizarVeterinarioUseCase implements IAtualizarVeterinarioUseCase {

    private final IVeterinarioGateway veterinarioGateway;

    public AtualizarVeterinarioUseCase(IVeterinarioGateway veterinarioGateway) {
        this.veterinarioGateway = veterinarioGateway;
    }

    @Override
    public Veterinario executar(Long id, Veterinario veterinario) throws UsuarioExistenteException, DomainInvalidException, UsuarioNaoEncontradoException {
        return veterinarioGateway.update(id, veterinario);
    }

}
