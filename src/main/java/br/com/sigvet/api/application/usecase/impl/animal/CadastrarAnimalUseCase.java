package br.com.sigvet.api.application.usecase.impl.animal;

import br.com.sigvet.api.application.exception.UsuarioExistsException;
import br.com.sigvet.api.core.domain.entities.Animal;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IAnimalGateway;
import br.com.sigvet.api.usecase.base.ICadastrarUseCase;

public class CadastrarAnimalUseCase implements ICadastrarUseCase<Animal> {

    private final IAnimalGateway animalGateway;

    public CadastrarAnimalUseCase(IAnimalGateway animalGateway) {
        this.animalGateway = animalGateway;
    }

    @Override
    public Animal executar(Animal type) throws DomainInvalidException, UsuarioExistsException {
        return animalGateway.save(type);
    }

}
