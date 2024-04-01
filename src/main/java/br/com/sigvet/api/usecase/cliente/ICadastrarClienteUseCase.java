package br.com.sigvet.api.usecase.cliente;

import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface ICadastrarClienteUseCase {
    Cliente executar(Cliente cliente) throws DomainInvalidException, UsuarioExistenteException;
}
