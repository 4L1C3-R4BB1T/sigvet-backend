package br.com.sigvet.api.usecase.vacina;

import org.springframework.data.domain.Page;

import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.domain.entities.Vacina;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IListarVacinasUseCase {
    Page<Vacina> executar(FilterModel filter) throws DomainInvalidException;
}
