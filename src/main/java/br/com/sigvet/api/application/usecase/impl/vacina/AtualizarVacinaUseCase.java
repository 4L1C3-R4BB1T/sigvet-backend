package br.com.sigvet.api.application.usecase.impl.vacina;


import br.com.sigvet.api.application.exception.UsuarioExistsException;
import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.core.domain.entities.Vacina;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVaccineGateway;
import br.com.sigvet.api.usecase.base.IAtualizarUseCase;

public class AtualizarVacinaUseCase implements IAtualizarUseCase<Vacina> {

    private final IVaccineGateway vacinaGateway;

    public AtualizarVacinaUseCase(IVaccineGateway vacinaGateway) {
        this.vacinaGateway = vacinaGateway;
    }

    @Override
    public Vacina executar(Long id, Vacina type)
            throws UsuarioExistsException, DomainInvalidException, UsuarioNotFoundException {
        return vacinaGateway.update(id, type);
    }

}
