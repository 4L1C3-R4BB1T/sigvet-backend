package br.com.sigvet.api.application.usecaseImpl.veterinario;

import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVeterinarioGateway;
import br.com.sigvet.api.usecase.base.IDeletarUseCase;

public class DeletarVeterinarioUseCase implements IDeletarUseCase<Veterinario> {

    private final IVeterinarioGateway veterinarioGateway;

    public DeletarVeterinarioUseCase(IVeterinarioGateway veterinarioGateway) {
        this.veterinarioGateway = veterinarioGateway;
    }

    @Override
    public boolean executar(Long id) throws UsuarioExistenteException, DomainInvalidException {
        return veterinarioGateway.delete(id);
    }

}
