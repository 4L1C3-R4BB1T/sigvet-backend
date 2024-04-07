package br.com.sigvet.api.application.usecase.impl.vacina;

import br.com.sigvet.api.application.exception.UsuarioExistsException;
import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.core.domain.entities.Vacina;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVaccineGateway;
import br.com.sigvet.api.usecase.base.IDeletarUseCase;

public class DeletarVacinaUseCase implements IDeletarUseCase<Vacina> {

    private final IVaccineGateway vacinaGateway;

    public DeletarVacinaUseCase(IVaccineGateway vacinaGateway) {
        this.vacinaGateway = vacinaGateway;
    }

    @Override
    public boolean executar(Long id)
            throws UsuarioExistsException, DomainInvalidException, UsuarioNotFoundException {
       return vacinaGateway.delete(id);
    }

}
