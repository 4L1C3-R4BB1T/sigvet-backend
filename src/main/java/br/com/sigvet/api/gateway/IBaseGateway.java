package br.com.sigvet.api.gateway;

import org.springframework.data.domain.Page;

import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IBaseGateway<T> {
    T save(T record) throws DomainInvalidException;
    T findById(Long id) throws DomainInvalidException;
    Page<T> findAll(FilterModel filter) throws DomainInvalidException;
    T update(Long id, T source);
    boolean delete(T target);
    
}
