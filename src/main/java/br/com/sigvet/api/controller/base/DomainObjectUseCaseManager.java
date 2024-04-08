package br.com.sigvet.api.controller.base;

import br.com.sigvet.api.usecase.base.IAtualizarUseCase;
import br.com.sigvet.api.usecase.base.ICadastrarUseCase;
import br.com.sigvet.api.usecase.base.IDeletarUseCase;
import br.com.sigvet.api.usecase.base.IListarUseCase;
import br.com.sigvet.api.usecase.base.IObterPorIdUseCase;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DomainObjectUseCaseManager<DomainObject> {

    private final ICadastrarUseCase<DomainObject> cadastrarUseCase;

    private final IListarUseCase<DomainObject> listarUseCase;

    private final IObterPorIdUseCase<DomainObject> obterPorIdUseCase;

    private final IAtualizarUseCase<DomainObject> atualizarUseCase;

    private final IDeletarUseCase<DomainObject> deletarUseCase;
}
