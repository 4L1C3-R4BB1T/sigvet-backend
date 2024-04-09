package br.com.sigvet.api.application.usecase.impl.vacina;

import br.com.sigvet.api.core.domain.entities.Vacina;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVaccineGateway;
import br.com.sigvet.api.usecase.base.ICadastrarUseCase;

public class CadastrarVacinaUseCase implements ICadastrarUseCase<Vacina> {

    private final IVaccineGateway vacinaGateway;

    public CadastrarVacinaUseCase(IVaccineGateway vacinaGateway) {
        this.vacinaGateway = vacinaGateway;
    }

    @Override
    public Vacina executar(Vacina type) throws DomainInvalidException {
        return vacinaGateway.save(type);
    }

}
