package br.com.sigvet.api.usecase.cliente;

import br.com.sigvet.api.core.domain.entities.Cliente;

public interface ICadastrarClienteUseCase {
    Cliente executar(Cliente cliente);
}
