package br.com.sigvet.api.usecase.veterinario;

import org.springframework.data.domain.Page;

import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IListarVeterinariosUseCase {
    Page<Veterinario> executar(FilterModel filter) throws DomainInvalidException; 
}
