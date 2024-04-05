package br.com.sigvet.api.application.usecaseImpl.vacina;

import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.core.domain.entities.Animal;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.usecase.base.IObterPorIdUseCase;

public class ObterVacinaPorIdUseCase implements IObterPorIdUseCase<Animal> {

    @Override
    public Animal executar(Long id) throws DomainInvalidException, UsuarioNaoEncontradoException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executar'");
    }

}
