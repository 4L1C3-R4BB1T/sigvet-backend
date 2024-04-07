package br.com.sigvet.api.application.usecase.impl.veterinario;


import br.com.sigvet.api.application.exception.UsuarioExistsException;
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVeterinarianGateway;
import br.com.sigvet.api.usecase.base.ICadastrarUseCase;

public class CadastrarVeterinarioUseCase implements ICadastrarUseCase<Veterinario> {

    private final IVeterinarianGateway veterinarioGateway;

    public CadastrarVeterinarioUseCase(IVeterinarianGateway veterinarioGateway) {
        this.veterinarioGateway = veterinarioGateway;
    }

    @Override
    public Veterinario executar(Veterinario veterinario) throws DomainInvalidException, UsuarioExistsException {
        return veterinarioGateway.save(veterinario);
    }

   

}
