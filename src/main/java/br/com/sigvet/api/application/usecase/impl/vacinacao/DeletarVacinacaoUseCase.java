package br.com.sigvet.api.application.usecase.impl.vacinacao;

import br.com.sigvet.api.application.exception.UsuarioExistsException;
import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.application.exception.VacinaNotFoundException;
import br.com.sigvet.api.core.domain.entities.Vacinacao;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVacinacaoGateway;
import br.com.sigvet.api.usecase.base.IDeletarUseCase;

public class DeletarVacinacaoUseCase implements IDeletarUseCase<Vacinacao> {
    
    private final IVacinacaoGateway vacinacaoGateway;
    
    public DeletarVacinacaoUseCase(IVacinacaoGateway vacinacaoGateway) {
        this.vacinacaoGateway = vacinacaoGateway;
    }

    @Override
    public boolean executar(Long id) throws UsuarioExistsException, DomainInvalidException, UsuarioNotFoundException, VacinaNotFoundException {
       return vacinacaoGateway.delete(id);
    }

}
