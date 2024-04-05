package br.com.sigvet.api.gateway;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IBaseGateway<TModel, TEntity> {
    TModel save(TModel record) throws DomainInvalidException, UsuarioExistenteException;
    TModel findById(Long id) throws DomainInvalidException, UsuarioNaoEncontradoException;
    Page<TModel> findAll(FilterModel filter) throws DomainInvalidException;
    TModel update(Long id, TModel source) throws UsuarioNaoEncontradoException, UsuarioExistenteException, DomainInvalidException ;
    boolean delete(Long id) throws UsuarioExistenteException;
    Specification<TEntity> buildSpecification(FilterModel filterModel);
}
