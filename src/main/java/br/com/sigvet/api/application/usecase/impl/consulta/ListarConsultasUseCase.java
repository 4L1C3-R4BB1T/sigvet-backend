package br.com.sigvet.api.application.usecase.impl.consulta;

import org.springframework.data.domain.Page;

import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.domain.entities.Consulta;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IConsultaGateway;
import br.com.sigvet.api.usecase.base.IListarUseCase;

public class ListarConsultasUseCase implements IListarUseCase<Consulta> {

    private final IConsultaGateway consultaGateway;

    public ListarConsultasUseCase(IConsultaGateway consultaGateway) {
        this.consultaGateway = consultaGateway;
    }

    @Override
    public Page<Consulta> executar(FilterModel filter) throws DomainInvalidException {
        return consultaGateway.findAll(filter);
    }

}
