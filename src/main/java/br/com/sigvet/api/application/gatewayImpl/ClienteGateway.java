package br.com.sigvet.api.application.gatewayImpl;

import static br.com.sigvet.api.application.utils.Utilities.logger;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.sigvet.api.application.builder.EntitySpecification;
import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.application.mapper.EnderecoMapper;
import br.com.sigvet.api.application.mapper.cliente.ClienteMapper;
import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.domain.entities.Cliente;
import br.com.sigvet.api.core.domain.entities.Endereco;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IClienteGateway;
import br.com.sigvet.api.infrastructure.entity.ClienteEntity;
import br.com.sigvet.api.infrastructure.entity.EnderecoEntity;
import br.com.sigvet.api.infrastructure.repository.CidadeJpaRepository;
import br.com.sigvet.api.infrastructure.repository.ClienteJpaRepository;
import br.com.sigvet.api.infrastructure.repository.EnderecoJpaRepository;
import br.com.sigvet.api.infrastructure.repository.UsuarioJpaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteGateway implements IClienteGateway {

    private final ClienteJpaRepository clienteJpaRepository;
    private final UsuarioJpaRepository usuarioJpaRepository;
    private final EnderecoJpaRepository enderecoJpaRepository;
    private final CidadeJpaRepository cidadeJpaRepository;
    private final ClienteMapper clienteMapper;
    private final EnderecoMapper enderecoMapper;

    @Transactional
    @Override
    public Cliente save(Cliente record) throws DomainInvalidException, UsuarioExistenteException {
        logger.info("Entrando no método ClienteGateway::save", record);
        
        // Verifica se o cliente fornecido não é nulo
        Assert.notNull(record, "O cliente fornecido não pode ser nulo");
        
        // Validações de dados do cliente
        validarEmailExistente(record.getEmail());
        validarCpfUnico(record.getCpf().getValor());
        validarUsuarioExistente(record.getUsuario());
        
        // Converte o cliente em uma entidade e salva no repositório
        ClienteEntity clienteEntity = clienteJpaRepository.save(clienteMapper.fromDomainToEntity(record));
        
        logger.info("Saíndo do método ClienteGateway::save");
        
        // Converte a entidade salva de volta para um objeto Cliente e retorna
        return clienteMapper.fromEntityToDomain(clienteEntity);
    }

    @Override
    public Cliente findById(Long id) throws DomainInvalidException, UsuarioNaoEncontradoException {
        // Verifica se o ID fornecido não é nulo
        Assert.notNull(id, "O id não pode ser nulo");
        
        logger.info("Buscando cliente pelo id " + id + " no método ClienteGateway::findById");
        
        // Busca o cliente pelo ID e converte a entidade para um objeto Cliente
        ClienteEntity clienteEntity = buscarClientePorId(id);
        
        logger.info("Cliente encontrado pelo id " + id + " no método ClienteGateway::findById");
        
        return clienteMapper.fromEntityToDomain(clienteEntity);
    }
    

    @Override
    public Page<Cliente> findAll(FilterModel filter) {
        logger.info("Buscando todos os clientes no método ClienteGateway::findAll");

        // Realiza a busca paginada dos clientes
        Page<ClienteEntity> pageClienteEntity = clienteJpaRepository.findAll(
                buildSpecification(filter),
                filter.toPageable());

        // Mapeia os resultados para objetos Cliente usando expressões lambda e streams
        List<Cliente> clientes = pageClienteEntity.getContent().stream()
                .map(clienteEntity -> {
                    try {
                        return clienteMapper.fromEntityToDomain(clienteEntity);
                    } catch (Exception ex) {
                        // Log a exceção e retorne null ou um objeto Cliente de fallback
                        logger.error("Erro ao converter clienteEntity para Cliente", ex);
                        return null;
                    }
                })
                .filter(Objects::nonNull) // Filtra elementos não nulos (caso ocorra alguma exceção)
                .toList();

        logger.info("Busca de todos os clientes finalizada no método ClienteGateway::findAll");

        // Retorna os resultados mapeados como uma Page
        return new PageImpl<>(clientes, pageClienteEntity.getPageable(), pageClienteEntity.getTotalElements());
    }

    @Transactional
    @Override
    public Cliente update(Long id, Cliente source)
            throws UsuarioNaoEncontradoException, UsuarioExistenteException, DomainInvalidException {
        Assert.notNull(id, "O id não pode ser nulo");
        Assert.notNull(source, "O cliente fornecido não pode ser nulo");

        // Verifica se o cliente com o ID fornecido existe
        var clienteEntity = buscarClientePorId(id);

        // Validações de dados atualizados
        if (!Objects.equals(source.getEmail(), clienteEntity.getEmail())) {
            validarEmailExistente(source.getEmail());
            clienteEntity.setEmail(source.getEmail());
        }
        
        if (!Objects.equals(source.getCpf().getValor(), clienteEntity.getCpf())) {
            validarCpfUnico(source.getCpf().getValor());
            clienteEntity.setCpf(source.getCpf().getValor());
        }

        if (!Objects.equals(source.getUsuario(), clienteEntity.getUsuario())) {
            validarUsuarioExistente(source.getUsuario());
            clienteEntity.setUsuario(source.getUsuario());
        }

        // Atualiza os demais campos do cliente
        clienteEntity.setNome(source.getNome());
        clienteEntity.setTelefone(source.getTelefone());
        clienteEntity.setSenha(source.getSenha()); // TODO: Implementar lógica para atualizar a senha de forma segura

        Endereco endereco = source.getEndereco();
        EnderecoEntity enderecoEntity = clienteEntity.getEndereco();

        if (enderecoEntity != null) {
            var cidadeEntity = cidadeJpaRepository.findById(endereco.getCidade().getId()).get();
            enderecoJpaRepository.deleteEnderecoByIdUsuario(clienteEntity.getId());
            clienteEntity.setEndereco(enderecoMapper.toEntity(endereco, clienteEntity, cidadeEntity));
            logger.info("Saindo da atualização de endereço ClienteGateway::Update");
        } else if (enderecoEntity == null) {
            logger.info("Entrando na criação de endereço ClienteGateway::Update");
            var cidadeEntity = cidadeJpaRepository.findById(endereco.getCidade().getId()).get();
            clienteEntity.setEndereco(enderecoMapper.toEntity(endereco, clienteEntity, cidadeEntity));
        }

        // Salva as alterações no repositório
        clienteJpaRepository.saveAndFlush(clienteEntity);

        return clienteMapper.fromEntityToDomain(clienteEntity);
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        logger.info("Entrando no método ClienteGateway::delete com id " + id);
        try {
            var usuarioOptional = usuarioJpaRepository.findById(id);
            if (usuarioOptional.isPresent()) {
                usuarioJpaRepository.deleteById(id);
                logger.info("A entidade com id " + id + " foi deletada");
                return true;
            } else { 
                logger.info("Não foi possível encontrar a entidade cliente com o id " + id);
                return false;
            }
        } catch (Exception ex) {
            logger.error("Erro ao excluir a entidade cliente com o id " + id, ex);
            return false;
        }
    }
    

    @Override
    public Specification<ClienteEntity> buildSpecification(FilterModel filterModel) {
        Specification<ClienteEntity> spec = Specification.where((root, query, cb) -> cb.equal(root.get("deleted"), false));

        for (var equalFilter : filterModel.getEqualFilters())
            spec = spec.and(EntitySpecification.equal(equalFilter, ClienteEntity.class));

        for (var inFilter : filterModel.getInFilters())
            spec = spec.and(EntitySpecification.in(inFilter, ClienteEntity.class));

        return spec;
    }

    public ClienteEntity buscarClientePorId(Long id) throws UsuarioNaoEncontradoException {
        logger.info("Entrando no método ClienteGateway::buscarClientePorId com id " + id);
        return Optional.ofNullable(clienteJpaRepository.findClienteByIdAndNotDeleted(id))
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Cliente não encontrado"));
    }

    private void validarExistencia(String atributo, String valor, String mensagemErro) throws UsuarioExistenteException {
        if (clienteJpaRepository.exists((root, query, cb) -> cb.and(cb.equal(cb.lower(root.get(atributo)), valor.trim().toLowerCase()), cb.equal(root.get("deleted"), false)))) {
            throw new UsuarioExistenteException(mensagemErro);
        }
    }
    
    private void validarEmailExistente(String email) throws UsuarioExistenteException {
        validarExistencia("email", email, "Email já em uso");
    }
    
    private void validarUsuarioExistente(String usuario) throws UsuarioExistenteException {
        validarExistencia("usuario", usuario, "Usuário já em uso");
    }
    
    private void validarCpfUnico(String cpf) throws UsuarioExistenteException {
        validarExistencia("cpf", cpf.replaceAll("\\D", ""), "CPF já em uso");
    }
    
}
