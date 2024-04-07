package br.com.sigvet.api.application.usecase.impl.veterinario;


import org.springframework.data.domain.Page;

import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVeterinarianGateway;
import br.com.sigvet.api.usecase.base.IListarUseCase;

public class ListarVeterinariosUseCase implements IListarUseCase<Veterinario> {

    private final IVeterinarianGateway veterinarioGateway;

    public ListarVeterinariosUseCase(IVeterinarianGateway veterinarioGateway) {
        this.veterinarioGateway = veterinarioGateway;
    }

    @Override
    public Page<Veterinario> executar(FilterModel filter) throws DomainInvalidException {
       return veterinarioGateway.findAll(filter);
    }

}
