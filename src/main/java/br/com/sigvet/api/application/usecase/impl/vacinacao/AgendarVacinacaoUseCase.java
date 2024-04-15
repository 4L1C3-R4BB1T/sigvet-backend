package br.com.sigvet.api.application.usecase.impl.vacinacao;

import br.com.sigvet.api.application.exception.UsuarioExistsException;
import br.com.sigvet.api.core.domain.entities.Vacinacao;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVacinacaoGateway;
import br.com.sigvet.api.usecase.base.ICadastrarUseCase;

public class AgendarVacinacaoUseCase implements ICadastrarUseCase<Vacinacao> {

    private final IVacinacaoGateway vacinacaoGateway;
    

    public AgendarVacinacaoUseCase(IVacinacaoGateway vacinacaoGateway) {
        this.vacinacaoGateway = vacinacaoGateway;
    }


    @Override
    public Vacinacao executar(Vacinacao type) throws DomainInvalidException, UsuarioExistsException {
        return vacinacaoGateway.save(type);
    }
    
}
