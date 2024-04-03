package br.com.sigvet.api.application.gatewayImpl;

import static br.com.sigvet.api.application.utils.Utilities.logger;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.sigvet.api.application.builder.EntitySpecification;
import br.com.sigvet.api.application.exception.ClienteNaoEncontradoException;
import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.application.mapper.ClienteMapper;
import br.com.sigvet.api.application.model.FilterModel;
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
    public Cliente save(Cliente record) throws DomainInvalidException, UsuarioExistenteException {
        logger.info("Criando usuário ClienteGateway::save");
        if (clienteJpaRepository.exists((root, query, cb) -> cb.or(
                cb.equal(cb.lower(root.get("email")), record.getEmail()),
                cb.equal(cb.lower(root.get("usuario")), record.getUsuario()),
                cb.equal(root.get("cpf"), record.getCpf().getValor())))) {
            throw new UsuarioExistenteException("O cliente já existe (Email, Usuário ou CPF)");
        }

        var clienteEntity = clienteJpaRepository.save(clienteMapper.toEntity(record));
        logger.info("Usuário criado ClienteGateway::save");
        return clienteMapper.toCliente(clienteEntity);
    }

    @Override
    public Cliente findById(Long id) throws DomainInvalidException, ClienteNaoEncontradoException {
        Assert.notNull(id, "O id não pode ser nulo");
        logger.info("Buscando cliente com id %d ClienteGateway::findById".formatted(id));
        var clienteEntity = clienteJpaRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));
        logger.info("Cliente com id %d no ClienteGateway::findById foi encontrado".formatted(id));
        return clienteMapper.toCliente(clienteEntity);
    }

    @Override
    public Page<Cliente> findAll(FilterModel filter) throws DomainInvalidException {
        logger.info("Buscando clientes ClienteGateway::findAll");
        Page<ClienteEntity> pageClienteEntity = clienteJpaRepository.findAll(
                buildSpecification(filter),
                filter.toPageable());

        List<Cliente> clientes = new ArrayList<>();

        for (var clienteEntity : pageClienteEntity.getContent()) {
            clientes.add(clienteMapper.toCliente(clienteEntity));
        }
        logger.info("Busca de clientes realizada ClienteGateway::findAll");
        return new PageImpl<>(clientes, pageClienteEntity.getPageable(), pageClienteEntity.getTotalElements());
    }

    @Override
    public Cliente update(Long id, Cliente source) throws ClienteNaoEncontradoException, UsuarioExistenteException, DomainInvalidException {
        Assert.notNull(id, "O id não pode ser nulo");

        var clienteEntity = clienteJpaRepository.findById(id)
                .orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));

        if (clienteJpaRepository.exists((root, query, cb) -> cb.or(
                cb.equal(cb.lower(root.get("email")), source.getEmail()),
                cb.equal(cb.lower(root.get("usuario")), source.getUsuario()),
                cb.equal(root.get("cpf"), source.getCpf().getValor())))
                && (!source.getEmail().equalsIgnoreCase(clienteEntity.getEmail())
                        || !source.getCpf().getValor().equalsIgnoreCase(clienteEntity.getCpf())
                        || !source.getUsuario().equalsIgnoreCase(clienteEntity.getUsuario()))) {
            throw new UsuarioExistenteException("O cliente já existe (Email, Usuário ou CPF)");
        }

        clienteEntity.setEmail(source.getEmail());
        clienteEntity.setNome(source.getNome());
        clienteEntity.setTelefone(source.getTelefone());
        clienteEntity.setCpf(source.getCpf().getValor());
        clienteEntity.setUsuario(source.getUsuario());
        clienteEntity.setSenha(source.getSenha());
        //TODO alterar senha e criptografar

        clienteJpaRepository.save(clienteEntity);

        return clienteMapper.toCliente(clienteEntity);
    }

    @Override
    public boolean delete(Long id) throws UsuarioExistenteException {
        Assert.notNull(id, "Cliente não pode ser nulo");
        return clienteJpaRepository.delete((root, query, cb) -> cb.equal(root.get("id"), id)) > 0;
    }

    @Override
    public Specification<ClienteEntity> buildSpecification(FilterModel filterModel) {
        Specification<ClienteEntity> spec = Specification.where(null);

        for (var equalFilter : filterModel.getEqualFilters())
            spec = spec.and(EntitySpecification.equal(equalFilter, ClienteEntity.class));

        for (var inFilter : filterModel.getInFilters())
            spec = spec.and(EntitySpecification.in(inFilter, ClienteEntity.class));

        return spec;
    }

}
