package br.com.sigvet.api.usecase.cliente;

import org.springframework.data.domain.Page;

import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IListarClientesUseCase {
    Page<Cliente> executar(FilterModel filter) throws DomainInvalidException;
}
