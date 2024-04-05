package br.com.sigvet.api.application.usecaseImpl.vacina;

import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.core.domain.entities.Vacina;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVacinaGateway;
import br.com.sigvet.api.usecase.base.IObterPorIdUseCase;

public class ObterVacinaPorIdUseCase implements IObterPorIdUseCase<Vacina> {

    private final IVacinaGateway vacinaGateway;

    public ObterVacinaPorIdUseCase(IVacinaGateway vacinaGateway) {
        this.vacinaGateway = vacinaGateway;
    }

    @Override
    public Vacina executar(Long id) throws DomainInvalidException, UsuarioNaoEncontradoException {
        return vacinaGateway.findById(id);
    }

}
