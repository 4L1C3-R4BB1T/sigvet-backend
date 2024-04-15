package br.com.sigvet.api.application.usecase.impl.vacinacao;

import org.springframework.data.domain.Page;

import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.domain.entities.Vacinacao;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVacinacaoGateway;
import br.com.sigvet.api.usecase.base.IListarUseCase;

public class ListarVacinacoesUseCase implements IListarUseCase<Vacinacao> {

    private final IVacinacaoGateway vacinacaoGateway;
    
    public ListarVacinacoesUseCase(IVacinacaoGateway vacinacaoGateway) {
        this.vacinacaoGateway = vacinacaoGateway;
    }

    @Override
    public Page<Vacinacao> executar(FilterModel filter) throws DomainInvalidException {
       return vacinacaoGateway.findAll(filter);
    }
}
