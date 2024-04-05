package br.com.sigvet.api.gateway;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;

import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.application.exception.VacinaNaoEncontradaException;
import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IBaseGateway<T, E> {
    T save(T record) throws DomainInvalidException, UsuarioExistenteException;
    T findById(Long id) throws DomainInvalidException, UsuarioNaoEncontradoException, VacinaNaoEncontradaException;
    Page<T> findAll(FilterModel filter) throws DomainInvalidException;
    T update(Long id, T source) throws UsuarioNaoEncontradoException, UsuarioExistenteException, DomainInvalidException ;
    boolean delete(Long id) throws UsuarioExistenteException;
    Specification<E> buildSpecification(FilterModel filterModel);
}
