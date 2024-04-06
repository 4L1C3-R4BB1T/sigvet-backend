package br.com.sigvet.api.application.usecase.impl.vacina;

import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.core.domain.entities.Vacina;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVacinaGateway;
import br.com.sigvet.api.usecase.base.IDeletarUseCase;

public class DeletarVacinaUseCase implements IDeletarUseCase<Vacina> {

    private final IVacinaGateway vacinaGateway;

    public DeletarVacinaUseCase(IVacinaGateway vacinaGateway) {
        this.vacinaGateway = vacinaGateway;
    }

    @Override
    public boolean executar(Long id)
            throws UsuarioExistenteException, DomainInvalidException, UsuarioNaoEncontradoException {
       return vacinaGateway.delete(id);
    }

}
