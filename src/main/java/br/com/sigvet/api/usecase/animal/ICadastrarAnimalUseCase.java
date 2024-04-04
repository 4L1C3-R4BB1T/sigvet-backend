package br.com.sigvet.api.usecase.animal;

import br.com.sigvet.api.core.domain.entities.Animal;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface ICadastrarAnimalUseCase {
    Animal executar(Animal animal) throws DomainInvalidException;
}
