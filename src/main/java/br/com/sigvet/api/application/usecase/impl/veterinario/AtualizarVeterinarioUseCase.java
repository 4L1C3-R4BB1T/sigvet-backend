package br.com.sigvet.api.application.usecase.impl.veterinario;

import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.application.exception.VacinaNotFoundException;
import br.com.sigvet.api.application.exception.UsuarioExistsException;
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVeterinarianGateway;
import br.com.sigvet.api.usecase.base.IAtualizarUseCase;

public class AtualizarVeterinarioUseCase implements IAtualizarUseCase<Veterinario> {

    private final IVeterinarianGateway veterinarioGateway;

    public AtualizarVeterinarioUseCase(IVeterinarianGateway veterinarioGateway) {
        this.veterinarioGateway = veterinarioGateway;
    }

    @Override
    public Veterinario executar(Long id, Veterinario veterinario) throws UsuarioExistsException, DomainInvalidException, UsuarioNotFoundException, VacinaNotFoundException {
        return veterinarioGateway.update(id, veterinario);
    }

}
