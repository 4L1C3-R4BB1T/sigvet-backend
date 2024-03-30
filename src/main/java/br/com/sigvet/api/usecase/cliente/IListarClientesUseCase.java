package br.com.sigvet.api.usecase.cliente;

import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.application.model.PageModel;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IListarClientesUseCase {
    PageModel<Cliente> executar(FilterModel filter) throws DomainInvalidException;
}
