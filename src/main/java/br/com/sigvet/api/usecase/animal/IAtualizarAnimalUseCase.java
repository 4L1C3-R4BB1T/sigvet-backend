package br.com.sigvet.api.usecase.animal;

import br.com.sigvet.api.application.exception.AnimalNaoEncontradoException;
import br.com.sigvet.api.core.domain.entities.Animal;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IAtualizarAnimalUseCase {
    Animal executar(Long id, Animal animal) throws AnimalNaoEncontradoException, DomainInvalidException;
}
