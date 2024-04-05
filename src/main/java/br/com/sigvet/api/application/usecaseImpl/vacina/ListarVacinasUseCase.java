package br.com.sigvet.api.application.usecaseImpl.vacina;

import org.springframework.data.domain.Page;

import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.domain.entities.Animal;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.usecase.base.IListarUseCase;

public class ListarVacinasUseCase implements IListarUseCase<Animal> {

    @Override
    public Page<Animal> executar(FilterModel filter) throws DomainInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'executar'");
    }

}
