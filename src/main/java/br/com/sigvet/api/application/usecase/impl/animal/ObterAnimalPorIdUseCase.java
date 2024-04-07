package br.com.sigvet.api.application.usecase.impl.animal;

import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.core.domain.entities.Animal;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IAnimalGateway;
import br.com.sigvet.api.usecase.base.IObterPorIdUseCase;

public class ObterAnimalPorIdUseCase implements IObterPorIdUseCase<Animal> {

    private final IAnimalGateway animalGateway;

    public ObterAnimalPorIdUseCase(IAnimalGateway animalGateway) {
        this.animalGateway = animalGateway;
    }

    @Override
    public Animal executar(Long id) throws DomainInvalidException, UsuarioNaoEncontradoException {
       return animalGateway.findById(id);
    }
}
