package br.com.sigvet.api.application.usecaseImpl.vacina;

import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.core.domain.entities.Vacina;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.usecase.base.IAtualizarUseCase;

public class AtualizarVacinaUseCase implements IAtualizarUseCase<Vacina> {

    @Override
    public Vacina executar(Long id, Vacina type)
            throws UsuarioExistenteException, DomainInvalidException, UsuarioNaoEncontradoException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executar'");
    }

}
