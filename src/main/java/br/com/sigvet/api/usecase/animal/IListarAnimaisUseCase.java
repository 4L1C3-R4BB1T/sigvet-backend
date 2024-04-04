package br.com.sigvet.api.usecase.animal;

import org.springframework.data.domain.Page;

import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.domain.entities.Animal;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IListarAnimaisUseCase {
    Page<Animal> executar(FilterModel filter) throws DomainInvalidException;
}
