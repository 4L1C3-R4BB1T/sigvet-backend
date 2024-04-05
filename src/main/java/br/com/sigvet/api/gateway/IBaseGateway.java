package br.com.sigvet.api.gateway;

import org.springframework.data.domain.Page;

import br.com.sigvet.api.application.exception.ClienteNaoEncontradoException;
import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.exception.DomainInvalidException;

public interface IBaseGateway<T> {
    T save(T record) throws DomainInvalidException, UsuarioExistenteException;
    T findById(Long id) throws DomainInvalidException, ClienteNaoEncontradoException;
    Page<T> findAll(FilterModel filter) throws DomainInvalidException;
    T update(Long id, T source) throws ClienteNaoEncontradoException, UsuarioExistenteException, DomainInvalidException ;
    boolean delete(Long id) throws UsuarioExistenteException;
}
