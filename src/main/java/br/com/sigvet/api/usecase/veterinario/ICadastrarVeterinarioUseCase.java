package br.com.sigvet.api.usecase.veterinario;

import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface ICadastrarVeterinarioUseCase {
    Veterinario executar(Veterinario veterinario) throws DomainInvalidException, UsuarioExistenteException;
}
