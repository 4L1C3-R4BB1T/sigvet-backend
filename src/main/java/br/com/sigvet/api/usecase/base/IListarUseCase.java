package br.com.sigvet.api.usecase.base;

import org.springframework.data.domain.Page;

import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IListarUseCase<T> {
    Page<T> executar(FilterModel filter) throws DomainInvalidException;
}
