package br.com.sigvet.api.application.mapper.base;

import br.com.sigvet.api.application.exception.CidadeNotFoundException;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.infrastructure.entity.BaseEntity;

public interface IBaseMapper<D, E extends BaseEntity, TCriarModel, TAtualizarModel> {
    
    E fromDomainToEntity(D source);

    D fromEntityToDomain(E source) throws DomainInvalidException;

    D fromCriarModelToDomain(TCriarModel source) throws DomainInvalidException, CidadeNotFoundException;

    D fromAtualizarModelToDomain(TAtualizarModel source) throws DomainInvalidException, CidadeNotFoundException;
}
