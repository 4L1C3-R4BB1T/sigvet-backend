package br.com.sigvet.api.usecase.diagnostico;

import br.com.sigvet.api.core.domain.entities.Diagnostico;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IRegistrarDiagnosticoUseCase {
    Diagnostico executar(Diagnostico diagnostico) throws DomainInvalidException;
}
