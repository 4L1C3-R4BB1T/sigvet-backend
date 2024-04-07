package br.com.sigvet.api.application.usecase.impl.vacina;
import org.springframework.data.domain.Page;

import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.domain.entities.Vacina;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVaccineGateway;
import br.com.sigvet.api.usecase.base.IListarUseCase;

public class ListarVacinasUseCase implements IListarUseCase<Vacina> {

    private final IVaccineGateway vacinaGateway;

    public ListarVacinasUseCase(IVaccineGateway vacinaGateway) {
        this.vacinaGateway = vacinaGateway;
    }

    @Override
    public Page<Vacina> executar(FilterModel filter) throws DomainInvalidException {
        return vacinaGateway.findAll(filter);
    }

}
