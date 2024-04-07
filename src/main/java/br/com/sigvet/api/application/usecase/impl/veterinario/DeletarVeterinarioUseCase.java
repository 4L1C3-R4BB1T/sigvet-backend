package br.com.sigvet.api.application.usecase.impl.veterinario;


import br.com.sigvet.api.application.exception.UsuarioExistsException;
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVeterinarianGateway;
import br.com.sigvet.api.usecase.base.IDeletarUseCase;

public class DeletarVeterinarioUseCase implements IDeletarUseCase<Veterinario> {

    private final IVeterinarianGateway veterinarioGateway;

    public DeletarVeterinarioUseCase(IVeterinarianGateway veterinarioGateway) {
        this.veterinarioGateway = veterinarioGateway;
    }

    @Override
    public boolean executar(Long id) throws UsuarioExistsException, DomainInvalidException {
        return veterinarioGateway.delete(id);
    }

}
