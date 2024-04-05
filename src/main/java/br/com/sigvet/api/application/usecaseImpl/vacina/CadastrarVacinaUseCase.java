package br.com.sigvet.api.application.usecaseImpl.vacina;

import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.core.domain.entities.Vacina;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.usecase.base.ICadastrarUseCase;

public class CadastrarVacinaUseCase implements ICadastrarUseCase<Vacina> {

    @Override
    public Vacina executar(Vacina type) throws DomainInvalidException, UsuarioExistenteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executar'");
    }

}
