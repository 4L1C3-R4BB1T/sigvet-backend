package br.com.sigvet.api.usecase.veterinario;

import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.application.exception.VeterinarioNaoEncontradoException;
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IAtualizarVeterinarioUseCase {
    Veterinario executar(Long id, Veterinario veterinario) throws VeterinarioNaoEncontradoException, UsuarioExistenteException, DomainInvalidException, UsuarioNaoEncontradoException;
}
