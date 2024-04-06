package br.com.sigvet.api.application.usecase.impl.vacina;


import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.core.domain.entities.Vacina;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVacinaGateway;
import br.com.sigvet.api.usecase.base.IAtualizarUseCase;

public class AtualizarVacinaUseCase implements IAtualizarUseCase<Vacina> {

    private final IVacinaGateway vacinaGateway;

    public AtualizarVacinaUseCase(IVacinaGateway vacinaGateway) {
        this.vacinaGateway = vacinaGateway;
    }

    @Override
    public Vacina executar(Long id, Vacina type)
            throws UsuarioExistenteException, DomainInvalidException, UsuarioNaoEncontradoException {
        return vacinaGateway.update(id, type);
    }

}
