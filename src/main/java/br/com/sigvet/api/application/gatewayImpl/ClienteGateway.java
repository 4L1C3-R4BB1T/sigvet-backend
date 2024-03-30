package br.com.sigvet.api.application.gatewayImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.api.application.mapper.ClienteMapper;
import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.application.model.PageModel;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IClienteGateway;
import br.com.sigvet.api.infrastructure.entity.ClienteEntity;
import br.com.sigvet.api.infrastructure.repository.ClienteJpaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteGateway implements IClienteGateway {

    private final ClienteJpaRepository clienteJpaRepository;

    private final ClienteMapper clienteMapper;
    
    @Transactional
    @Override
    public Cliente save(Cliente record) throws DomainInvalidException {
        var clienteSaved = clienteJpaRepository.save(clienteMapper.toEntity(record));
        return clienteMapper.toCliente(clienteSaved);
    }

    @Override
    public Cliente findById(Long id) throws DomainInvalidException {
        var clienteSaved = clienteJpaRepository.findById(id);

        if (clienteSaved.isEmpty()) {
            return null;
        }

        return clienteMapper.toCliente(clienteSaved.get());
    }

    @Override
    public PageModel<Cliente> findAll(FilterModel filter) throws DomainInvalidException {

        List<Cliente> clientes = new ArrayList<>();
        Page<ClienteEntity> page = clienteJpaRepository.findAll(filter.toPageable());

        for (var cliente: page.getContent()) {
            clientes.add(clienteMapper.toCliente(cliente));
        }

       return new PageModel<Cliente>(clientes, page);
    }

    @Override
    public Cliente update(Long id, Cliente source) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(Cliente target) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }
    
}
