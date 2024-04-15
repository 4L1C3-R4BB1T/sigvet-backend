package br.com.sigvet.api.application.usecase.impl.animal;

import br.com.sigvet.api.core.domain.entities.Animal;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IAnimalGateway;
import br.com.sigvet.api.usecase.base.IAtualizarUseCase;

public class AtualizarAnimalUseCase implements IAtualizarUseCase<Animal> {

    private final IAnimalGateway animalGateway;

    public AtualizarAnimalUseCase(IAnimalGateway animalGateway) {
        this.animalGateway = animalGateway;
    }

    @Override
    public Animal executar(Long id, Animal type) throws DomainInvalidException {
       return animalGateway.update(id, type);
    }

}
