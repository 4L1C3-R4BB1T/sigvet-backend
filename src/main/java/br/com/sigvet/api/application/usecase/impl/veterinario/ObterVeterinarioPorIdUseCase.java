package br.com.sigvet.api.application.usecase.impl.veterinario;


import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVeterinarianGateway;
import br.com.sigvet.api.usecase.base.IObterPorIdUseCase;

public class ObterVeterinarioPorIdUseCase implements IObterPorIdUseCase<Veterinario> {

    private final IVeterinarianGateway veterinarioGateway;

    public ObterVeterinarioPorIdUseCase(IVeterinarianGateway veterinarioGateway) {
        this.veterinarioGateway = veterinarioGateway;
    }

    @Override
    public Veterinario executar(Long id) throws DomainInvalidException, UsuarioNotFoundException {
        return veterinarioGateway.findById(id);
    }
}
