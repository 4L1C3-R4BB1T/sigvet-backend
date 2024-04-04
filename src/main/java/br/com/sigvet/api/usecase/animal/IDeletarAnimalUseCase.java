package br.com.sigvet.api.usecase.animal;

import br.com.sigvet.api.application.exception.AnimalNaoEncontradoException;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IDeletarAnimalUseCase {
    boolean executar(Long id) throws DomainInvalidException, AnimalNaoEncontradoException;
}
