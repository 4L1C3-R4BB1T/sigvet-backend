package br.com.sigvet.api.usecase.veterinario;

import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.application.exception.VeterinarioNaoEncontradoException;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IDeletarVeterinarioUseCase {
    boolean executar(Long id) throws UsuarioExistenteException, DomainInvalidException, VeterinarioNaoEncontradoException;
}
