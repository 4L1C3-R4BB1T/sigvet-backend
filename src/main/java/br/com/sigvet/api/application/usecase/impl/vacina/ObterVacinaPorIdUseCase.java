package br.com.sigvet.api.application.usecase.impl.vacina;
import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.core.domain.entities.Vacina;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVaccineGateway;
import br.com.sigvet.api.usecase.base.IObterPorIdUseCase;

public class ObterVacinaPorIdUseCase implements IObterPorIdUseCase<Vacina> {

    private final IVaccineGateway vacinaGateway;

    public ObterVacinaPorIdUseCase(IVaccineGateway vacinaGateway) {
        this.vacinaGateway = vacinaGateway;
    }

    @Override
    public Vacina executar(Long id) throws DomainInvalidException, UsuarioNotFoundException {
        return vacinaGateway.findById(id);
    }

}
