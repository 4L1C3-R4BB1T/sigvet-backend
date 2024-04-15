package br.com.sigvet.api.application.usecase.impl.vacinacao;

import br.com.sigvet.api.application.exception.UsuarioExistsException;
import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.application.exception.VacinaNotFoundException;
import br.com.sigvet.api.core.domain.entities.Vacinacao;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.usecase.base.IAtualizarUseCase;

// É apenas um modelo
public class AtualizarVacinacaoUseCase implements IAtualizarUseCase<Vacinacao> {

    @Override
    public Vacinacao executar(Long id, Vacinacao type)
            throws UsuarioExistsException, DomainInvalidException, UsuarioNotFoundException, VacinaNotFoundException {
       return null;
    }
    
}
