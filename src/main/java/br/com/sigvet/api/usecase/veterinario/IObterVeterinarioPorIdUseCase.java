package br.com.sigvet.api.usecase.veterinario;

import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IObterVeterinarioPorIdUseCase {
    Veterinario executar(Long id) throws DomainInvalidException, UsuarioNaoEncontradoException;
}
