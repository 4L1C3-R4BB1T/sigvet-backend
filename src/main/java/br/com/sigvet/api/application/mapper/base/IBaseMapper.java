package br.com.sigvet.api.application.mapper.base;

import br.com.sigvet.api.application.exception.CidadeNaoExistenteException;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.infrastructure.entity.BaseEntity;

public interface IBaseMapper<TDomain, TEntity extends BaseEntity, TCriarModel, TAtualizarModel> {
    
    TEntity fromDomainToEntity(TDomain source);

    TDomain fromEntityToDomain(TEntity source) throws DomainInvalidException;

    TDomain fromCriarModelToDomain(TCriarModel source) throws DomainInvalidException, CidadeNaoExistenteException;

    TDomain fromAtualizarModelToDomain(TAtualizarModel source) throws DomainInvalidException, CidadeNaoExistenteException;
}
