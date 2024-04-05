package br.com.sigvet.api.application.usecaseImpl.veterinario;

import org.springframework.data.domain.Page;

import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVeterinarioGateway;
import br.com.sigvet.api.usecase.veterinario.IListarVeterinariosUseCase;

public class ListarVeterinariosUseCase implements IListarVeterinariosUseCase {

    private final IVeterinarioGateway veterinarioGateway;

    public ListarVeterinariosUseCase(IVeterinarioGateway veterinarioGateway) {
        this.veterinarioGateway = veterinarioGateway;
    }

    @Override
    public Page<Veterinario> executar(FilterModel filter) throws DomainInvalidException {
       return veterinarioGateway.findAll(filter);
    }

}
