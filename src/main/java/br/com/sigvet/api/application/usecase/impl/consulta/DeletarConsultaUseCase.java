package br.com.sigvet.api.application.usecase.impl.consulta;

import br.com.sigvet.api.core.domain.entities.Consulta;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IConsultaGateway;
import br.com.sigvet.api.usecase.base.IDeletarUseCase;

public class DeletarConsultaUseCase implements IDeletarUseCase<Consulta> {

    private final IConsultaGateway consultaGateway;

    public DeletarConsultaUseCase(IConsultaGateway consultaGateway) {
        this.consultaGateway = consultaGateway;
    }

    @Override
    public boolean executar(Long id) throws DomainInvalidException {
        return consultaGateway.delete(id);
    }

}
