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
import br.com.sigvet.api.infrastructure.repository.UsuarioJpaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteGateway implements IClienteGateway {

    private final ClienteJpaRepository clienteJpaRepository;
    private final UsuarioJpaRepository usuarioJpaRepository;
    private final ClienteMapper clienteMapper;

    @Transactional
    @Override
    public Cliente save(Cliente record) throws DomainInvalidException, UsuarioExistenteException {
        logger.info("Criando usuário ClienteGateway::save", record);

        validarEmailExistente(record.getEmail());
        validarCpfExistente(record.getCpf().getValor());
        validarUsuarioExistente(record.getUsuario());

        var clienteEntity = clienteJpaRepository.save(clienteMapper.toEntity(record));

        logger.info("Usuário criado ClienteGateway::save");

        return clienteMapper.toCliente(clienteEntity);
    }

    @Override
    public Cliente findById(Long id) throws DomainInvalidException, ClienteNaoEncontradoException {
        Assert.notNull(id, "O id não pode ser nulo");
        logger.info("Buscando cliente com id %d ClienteGateway::findById".formatted(id));
        return clienteMapper.toCliente(buscarClientePorId(id));
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

        var clienteEntity = buscarClientePorId(id);

        if (!source.getEmail().equalsIgnoreCase(clienteEntity.getEmail())) {
            validarEmailExistente(source.getEmail());
        }
        
        if (!source.getCpf().getValor().equalsIgnoreCase(clienteEntity.getCpf())) {
            validarCpfExistente(source.getCpf().getValor());
        }

        if (!source.getUsuario().equalsIgnoreCase(clienteEntity.getUsuario())) {
            validarUsuarioExistente(source.getUsuario());
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

    @Transactional
    @Override
    public boolean delete(Long id) throws UsuarioExistenteException {
        logger.info("Entrando no método ClienteGateway::delete com id " + id);
        try {
            buscarClientePorId(id);
            usuarioJpaRepository.deleteById(id);
            logger.info("A entidade com id " + id + "foi deletada");
            return true;
        } catch (Exception ex) {
            logger.info("Não foi possível deletar entidade cliente com o id " + id);
            return false;
        }
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

    public ClienteEntity buscarClientePorId(Long id) throws ClienteNaoEncontradoException {
        return clienteJpaRepository.findById(id).orElseThrow(() -> new ClienteNaoEncontradoException("Cliente não encontrado"));
    }
    

    private void validarEmailExistente(String email) throws UsuarioExistenteException {
        if (clienteJpaRepository.exists((root, query, cb) -> cb.equal(root.get("email"), email))) {
            throw new UsuarioExistenteException("Email já em uso");
        }
    }
    
    private void validarUsuarioExistente(String usuario) throws UsuarioExistenteException {
        if (clienteJpaRepository.exists((root, query, cb) -> cb.equal(root.get("usuario"), usuario))) {
            throw new UsuarioExistenteException("Usuário já em uso");
        }
    }
    
    private void validarCpfExistente(String cpf) throws UsuarioExistenteException {
        if (clienteJpaRepository.exists((root, query, cb) -> cb.equal(root.get("cpf"), cpf))) {
            throw new UsuarioExistenteException("CPF já em uso");
        }
    }

}
