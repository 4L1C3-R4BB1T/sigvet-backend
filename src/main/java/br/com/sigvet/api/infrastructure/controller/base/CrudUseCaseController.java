package br.com.sigvet.api.infrastructure.controller.base;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.sigvet.api.application.mapper.base.IBaseMapper;
import br.com.sigvet.api.infrastructure.entity.BaseEntity;
import br.com.sigvet.api.usecase.base.IAtualizarUseCase;
import br.com.sigvet.api.usecase.base.ICadastrarUseCase;
import br.com.sigvet.api.usecase.base.IDeletarUseCase;
import br.com.sigvet.api.usecase.base.IListarUseCase;
import br.com.sigvet.api.usecase.base.IObterPorIdUseCase;

/**
 * @param <T> Classe de domínio
 * @param <E> Classe de entidade
 * @param <C> Classe de parâmetro para o método post
 * @param <A> Classe de parâmetro para o método put
 * @param <K> Classe para o Mapper
 * @param <J> Classe para mapear DTO
 */
public class CrudUseCaseController<T, E extends BaseEntity, C, A, K extends IBaseMapper<T, E, C, A>, J> {
    
    @Autowired 
    protected ICadastrarUseCase<T> cadastrarUseCase;

    @Autowired
    protected IListarUseCase<T> listarUseCase;

    @Autowired
    protected IObterPorIdUseCase<T> obterPorIdUseCase;

    @Autowired
    protected IAtualizarUseCase<T> atualizarUseCase;

    @Autowired
    protected IDeletarUseCase<T> deletarUseCase;

    @Autowired
    protected K mapper;

    @Autowired
    protected J DTOMapper;
}
