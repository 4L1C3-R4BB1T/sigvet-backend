package br.com.sigvet.api.application.usecase.impl.animal;

import org.springframework.data.domain.Page;

import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.domain.entities.Animal;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IAnimalGateway;
import br.com.sigvet.api.usecase.base.IListarUseCase;

public class ListarAnimaisUseCase implements IListarUseCase<Animal> {

    private final IAnimalGateway animalGateway;

    public ListarAnimaisUseCase(IAnimalGateway animalGateway) {
        this.animalGateway = animalGateway;
    }

    @Override
    public Page<Animal> executar(FilterModel filter) throws DomainInvalidException {
        return animalGateway.findAll(filter);
    }

    

}
