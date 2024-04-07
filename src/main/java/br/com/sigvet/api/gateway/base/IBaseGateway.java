package br.com.sigvet.api.gateway.base;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.application.exception.VacinaNotFoundException;
import br.com.sigvet.api.application.exception.UsuarioExistsException;
import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IBaseGateway<T, E> {
    T save(T record) throws DomainInvalidException, UsuarioExistsException;
    T findById(Long id) throws DomainInvalidException, UsuarioNotFoundException, VacinaNotFoundException;
    Page<T> findAll(FilterModel filter) throws DomainInvalidException;
    T update(Long id, T source) throws UsuarioNotFoundException, UsuarioExistsException, DomainInvalidException ;
    boolean delete(Long id) throws UsuarioExistsException;
    Specification<E> buildSpecification(FilterModel filterModel);
}
