package br.com.sigvet.api.application.usecase.impl.vacina;
import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.core.domain.entities.Vacina;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVacinaGateway;
import br.com.sigvet.api.usecase.base.ICadastrarUseCase;

public class CadastrarVacinaUseCase implements ICadastrarUseCase<Vacina> {

    private final IVacinaGateway vacinaGateway;

    public CadastrarVacinaUseCase(IVacinaGateway vacinaGateway) {
        this.vacinaGateway = vacinaGateway;
    }

    @Override
    public Vacina executar(Vacina type) throws DomainInvalidException, UsuarioExistenteException {
        return vacinaGateway.save(type);
    }

}
