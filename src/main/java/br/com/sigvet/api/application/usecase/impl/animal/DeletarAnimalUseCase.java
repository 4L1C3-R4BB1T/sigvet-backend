package br.com.sigvet.api.application.usecase.impl.animal;

import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.core.domain.entities.Animal;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IAnimalGateway;
import br.com.sigvet.api.usecase.base.IDeletarUseCase;

public class DeletarAnimalUseCase implements IDeletarUseCase<Animal> {

    private final IAnimalGateway animalGateway;

    public DeletarAnimalUseCase(IAnimalGateway animalGateway) {
        this.animalGateway = animalGateway;
    }

    @Override
    public boolean executar(Long id)
            throws UsuarioExistenteException, DomainInvalidException, UsuarioNaoEncontradoException {
        return animalGateway.delete(id);
    }

}
