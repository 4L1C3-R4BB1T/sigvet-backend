package br.com.sigvet.api.infrastructure.controller.base;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sigvet.api.application.mapper.base.IBaseMapper;
import br.com.sigvet.api.infrastructure.entity.BaseEntity;
import br.com.sigvet.api.usecase.base.IAtualizarUseCase;
import br.com.sigvet.api.usecase.base.ICadastrarUseCase;
import br.com.sigvet.api.usecase.base.IDeletarUseCase;
import br.com.sigvet.api.usecase.base.IListarUseCase;
import br.com.sigvet.api.usecase.base.IObterPorIdUseCase;

public class CrudUseCaseController<D, E extends BaseEntity, TCriarModel, TAtualizarModel, TMapper1 extends IBaseMapper<D, E, TCriarModel, TAtualizarModel>, TMapper2> {
    
    @Autowired 
    protected ICadastrarUseCase<D> cadastrarUseCase;

    @Autowired
    protected IListarUseCase<D> listarUseCase;

    @Autowired
    protected IObterPorIdUseCase<D> obterPorIdUseCase;

    @Autowired
    protected IAtualizarUseCase<D> atualizarUseCase;

    @Autowired
    protected IDeletarUseCase<D> deletarUseCase;

    @Autowired
    protected TMapper1 mapper;

    @Autowired
    protected TMapper2 DTOMapper;
}
