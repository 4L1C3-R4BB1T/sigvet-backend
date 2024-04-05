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
import br.com.sigvet.api.application.exception.CrmvUFNaoEncontradoException;
import br.com.sigvet.api.application.exception.UsuarioExistenteException;
import br.com.sigvet.api.application.exception.UsuarioNaoEncontradoException;
import br.com.sigvet.api.application.mapper.EnderecoMapper;
import br.com.sigvet.api.application.mapper.veterinario.VeterinarioMapper;
import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.domain.entities.Endereco;
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVeterinarioGateway;
import br.com.sigvet.api.infrastructure.entity.EnderecoEntity;
import br.com.sigvet.api.infrastructure.entity.VeterinarioEntity;
import br.com.sigvet.api.infrastructure.repository.CidadeJpaRepository;
import br.com.sigvet.api.infrastructure.repository.EnderecoJpaRepository;
import br.com.sigvet.api.infrastructure.repository.UFJpaRepository;
import br.com.sigvet.api.infrastructure.repository.UsuarioJpaRepository;
import br.com.sigvet.api.infrastructure.repository.VeterinarioJpaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VeterinarioGateway implements IVeterinarioGateway {

    private final VeterinarioJpaRepository veterinarioJpaRepository;
    private final UsuarioJpaRepository usuarioJpaRepository;
    private final EnderecoJpaRepository enderecoJpaRepository;
    private final CidadeJpaRepository cidadeJpaRepository;
    private final UFJpaRepository ufJpaRepository;
    private final VeterinarioMapper veterinarioMapper;
    private final EnderecoMapper enderecoMapper;

    @Transactional
    @Override
    public Veterinario save(Veterinario record) throws DomainInvalidException, UsuarioExistenteException, CrmvUFNaoEncontradoException {
        logger.info("Entrando no método VeterinarioGateway::save", record);
        
        // Verifica se o veterinario fornecido não é nulo
        Assert.notNull(record, "O veterinario fornecido não pode ser nulo");

        if (ufJpaRepository.findById(record.getCrmvUf().toUpperCase()).isEmpty()) {
            throw new CrmvUFNaoEncontradoException("UF do crmv não encontrada");
        }
        
        // Validações de dados do veterinario
        validarCrmvUnico(record.getCrmv(), record.getCrmvUf());
        validarEmailExistente(record.getEmail());
        validarCpfUnico(record.getCpf().getValor());
        validarUsuarioExistente(record.getUsuario());

    
        
        // Converte o veterinario em uma entidade e salva no repositório
        VeterinarioEntity veterinarioEntity = veterinarioJpaRepository.save(veterinarioMapper.fromDomainToEntity(record));
        
        logger.info("Saíndo do método VeterinarioGateway::save");
        
        // Converte a entidade salva de volta para um objeto Veterinario e retorna
        return veterinarioMapper.fromEntityToDomain(veterinarioEntity);
    }

    @Override
    public Veterinario findById(Long id) throws DomainInvalidException, UsuarioNaoEncontradoException {
        // Verifica se o ID fornecido não é nulo
        Assert.notNull(id, "O id não pode ser nulo");
        
        logger.info("Buscando cliente pelo id " + id + " no método VeterinarioGateway::findById");
        
        // Busca o veterinario pelo ID e converte a entidade para um objeto Veterinario
        VeterinarioEntity veterinarioEntity = buscarVeterinarioPorId(id);
        
        logger.info("Veterinario encontrado pelo id " + id + " no método VeterinarioGateway::findById");
        
        return veterinarioMapper.fromEntityToDomain(veterinarioEntity);
    }
    

    @Override
    public Page<Veterinario> findAll(FilterModel filter) {
        logger.info("Buscando todos os veterinarios no método VeterinarioGateway::findAll");

        // Realiza a busca paginada dos veterinaarios
        Page<VeterinarioEntity> pageVeterinarioEntity = veterinarioJpaRepository.findAll(
                buildSpecification(filter),
                filter.toPageable());

        // Mapeia os resultados para objetos Veterionario usando expressões lambda e streams
        List<Veterinario> veterinarios = pageVeterinarioEntity.getContent().stream()
                .map(veterinarioEntity -> {
                    try {
                        return veterinarioMapper.fromEntityToDomain(veterinarioEntity);
                    } catch (Exception ex) {
                        // Log a exceção e retorne null ou um objeto Veterinario de fallback
                        logger.error("Erro ao converter veterionarioEntity para Veterinario", ex);
                        return null;
                    }
                })
                .filter(Objects::nonNull) // Filtra elementos não nulos (caso ocorra alguma exceção)
                .toList();

        logger.info("Busca de todos os veterinarios finalizada no método VeterinarioGateway::findAll");

        // Retorna os resultados mapeados como uma Page
        return new PageImpl<>(veterinarios, pageVeterinarioEntity.getPageable(), pageVeterinarioEntity.getTotalElements());
    }

    @Transactional
    @Override
    public Veterinario update(Long id, Veterinario source)
            throws UsuarioNaoEncontradoException, UsuarioExistenteException, DomainInvalidException {
        Assert.notNull(id, "O id não pode ser nulo");
        Assert.notNull(source, "O cliente fornecido não pode ser nulo");

        // Verifica se o cliente com o ID fornecido existe
        var veterinarioEntity = buscarVeterinarioPorId(id);

        // Validações de dados atualizados
        if (!Objects.equals(source.getEmail(), veterinarioEntity.getEmail())) {
            validarEmailExistente(source.getEmail());
            veterinarioEntity.setEmail(source.getEmail());
        }
        
        if (!Objects.equals(source.getCpf().getValor(), veterinarioEntity.getCpf())) {
            validarCpfUnico(source.getCpf().getValor());
            veterinarioEntity.setCpf(source.getCpf().getValor());
        }

        if (!Objects.equals(source.getUsuario(), veterinarioEntity.getUsuario())) {
            validarUsuarioExistente(source.getUsuario());
            veterinarioEntity.setUsuario(source.getUsuario());
        }

        if (!Objects.equals(source.getCrmv(), veterinarioEntity.getCrmv())) {
            validarCrmvUnico(source.getCrmv(), source.getCrmvUf());

            veterinarioEntity.setCrmv(source.getCrmv());

            if (ufJpaRepository.findById(source.getCrmvUf().toUpperCase()).isEmpty()) {
                throw new CrmvUFNaoEncontradoException("UF do crmv não encontrada");
            }

            veterinarioEntity.setCrmvUf(source.getCrmvUf());
        }

        // Atualiza os demais campos do veterinario
        veterinarioEntity.setNome(source.getNome());
        veterinarioEntity.setTelefone(source.getTelefone());
        veterinarioEntity.setSenha(source.getSenha()); // TODO: Implementar lógica para atualizar a senha de forma segura
        veterinarioEntity.setEspecialidade(source.getEspecialidade());

        Endereco endereco = source.getEndereco();
        EnderecoEntity enderecoEntity = veterinarioEntity.getEndereco();

        if (enderecoEntity != null) {
            var cidadeEntity = cidadeJpaRepository.findById(endereco.getCidade().getId()).get();
            enderecoJpaRepository.deleteEnderecoByIdUsuario(veterinarioEntity.getId());
            veterinarioEntity.setEndereco(enderecoMapper.toEntity(endereco, veterinarioEntity, cidadeEntity));
            logger.info("Saindo da atualização de endereço ClienteGateway::Update");
        } else if (enderecoEntity == null) {
            logger.info("Entrando na criação de endereço ClienteGateway::Update");
            var cidadeEntity = cidadeJpaRepository.findById(endereco.getCidade().getId()).get();
            veterinarioEntity.setEndereco(enderecoMapper.toEntity(endereco, veterinarioEntity, cidadeEntity));
        }


        // Salva as alterações no repositório
        veterinarioJpaRepository.saveAndFlush(veterinarioEntity);

        return veterinarioMapper.fromEntityToDomain(veterinarioEntity);
    }

    @Transactional
    @Override
    public boolean delete(Long id) {
        logger.info("Entrando no método ClienteGateway::delete com id " + id);
        try {
            buscarVeterinarioPorId(id);
            usuarioJpaRepository.deleteById(id);
            logger.info("A entidade com id " + id + " foi deletada");
            return true;
        } catch (Exception ex) {
            logger.error("Erro ao excluir a entidade cliente com o id " + id, ex);
            return false;
        }
    }
    

    @Override
    public Specification<VeterinarioEntity> buildSpecification(FilterModel filterModel) {
        Specification<VeterinarioEntity> spec = Specification.where((root, query, cb) -> cb.equal(root.get("deleted"), false));

        for (var equalFilter : filterModel.getEqualFilters())
            spec = spec.and(EntitySpecification.equal(equalFilter, VeterinarioEntity.class));

        for (var inFilter : filterModel.getInFilters())
            spec = spec.and(EntitySpecification.in(inFilter, VeterinarioEntity.class));

        return spec;
    }

    public VeterinarioEntity buscarVeterinarioPorId(Long id) throws UsuarioNaoEncontradoException {
        logger.info("Entrando no método VeterinarioGateway::buscarVeterionarioPorId com id " + id);
        return Optional.ofNullable(veterinarioJpaRepository.findVeterinarioByIdAndNotDeleted(id))
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Veterinário não encontrado"));
    }

    private void validarExistencia(String atributo, String valor, String mensagemErro) throws UsuarioExistenteException {
        if (veterinarioJpaRepository.exists((root, query, cb) -> cb.and(cb.equal(cb.lower(root.get(atributo)), valor.trim().toLowerCase()), cb.equal(root.get("deleted"), false)))) {
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

    private void validarCrmvUnico(String cmrv, String crmvUf) throws UsuarioExistenteException {
        if (veterinarioJpaRepository.findByCrmvAndCrmvUf(cmrv, crmvUf).isPresent()) {
            throw new UsuarioExistenteException("Crmv já em uso");
        }
    }
    
}
