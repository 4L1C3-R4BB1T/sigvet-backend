package br.com.sigvet.api.usecase.vacina;

import br.com.sigvet.api.core.domain.entities.Vacina;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface ICadastrarVeterinarioUseCase {
    Vacina executar(Vacina vacina) throws DomainInvalidException;
}
