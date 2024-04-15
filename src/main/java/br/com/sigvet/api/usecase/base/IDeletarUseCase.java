package br.com.sigvet.api.usecase.base;

import br.com.sigvet.api.application.exception.UsuarioExistsException;
import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.application.exception.VacinaNotFoundException;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IDeletarUseCase<T> {
    boolean executar(Long id) throws UsuarioExistsException, DomainInvalidException, UsuarioNotFoundException, VacinaNotFoundException;
}
