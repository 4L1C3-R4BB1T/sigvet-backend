package  br.com.sigvet.api.application.usecase.impl.consulta;

import br.com.sigvet.api.core.domain.entities.Consulta;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IConsultaGateway;
import br.com.sigvet.api.usecase.base.ICadastrarUseCase;

public class AgendarConsultaUseCase implements ICadastrarUseCase<Consulta> {

    private final IConsultaGateway consultaGateway;

    public AgendarConsultaUseCase(IConsultaGateway consultaGateway) {
        this.consultaGateway = consultaGateway;
    }

    @Override
    public Consulta executar(Consulta type) throws DomainInvalidException {
        return consultaGateway.save(type);
    }

}
