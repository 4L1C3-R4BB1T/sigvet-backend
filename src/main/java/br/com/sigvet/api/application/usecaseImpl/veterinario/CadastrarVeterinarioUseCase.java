package br.com.sigvet.api.application.usecaseImpl.veterinario;

import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVeterinarioGateway;
import br.com.sigvet.api.usecase.veterinario.ICadastrarVeterinarioUseCase;

public class CadastrarVeterinarioUseCase implements ICadastrarVeterinarioUseCase {

    private final IVeterinarioGateway veterinarioGateway;

    public CadastrarVeterinarioUseCase(IVeterinarioGateway veterinarioGateway) {
        this.veterinarioGateway = veterinarioGateway;
    }

    @Override
    public Veterinario executar(Veterinario veterinario) throws DomainInvalidException, UsuarioExistenteException {
        return veterinarioGateway.save(veterinario);
    }

   

}
