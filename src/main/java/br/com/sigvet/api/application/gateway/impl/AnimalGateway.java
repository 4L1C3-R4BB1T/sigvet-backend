package br.com.sigvet.api.application.gateway.impl;

import static br.com.sigvet.api.infrastructure.utils.Utilities.logger;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.sigvet.api.application.builder.EntitySpecification;
import br.com.sigvet.api.application.exception.AnimalNotFoundException;
import br.com.sigvet.api.application.exception.UsuarioExistsException;
import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.application.exception.VacinaNotFoundException;
import br.com.sigvet.api.application.mapper.animal.AnimalMapper;
import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.domain.entities.Animal;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IAnimalGateway;
import br.com.sigvet.api.infrastructure.entity.AnimalEntity;
import br.com.sigvet.api.infrastructure.repository.AnimalJpaRepository;
import br.com.sigvet.api.infrastructure.repository.ClienteJpaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnimalGateway implements IAnimalGateway {

    private final AnimalJpaRepository animalJpaRepository;
    private final ClienteJpaRepository clienteJpaRepository;
    private final AnimalMapper animalMapper;

    @Transactional
    @Override
    public Animal save(Animal record) throws DomainInvalidException, UsuarioExistsException {

        var cliente = record.getCliente();

        if (Objects.isNull(cliente)) {
            throw new UsuarioExistsException("O cliente não pode ser nulo");
        }

        if (clienteJpaRepository.findClienteByIdAndNotDeleted(cliente.getId()) == null) {
            throw new UsuarioExistsException("Cliente com %d não encontrado".formatted(cliente.getId()));
        }

        var animalEntity = animalMapper.fromDomainToEntity(record);
  
        return animalMapper.fromEntityToDomain(animalJpaRepository.save(animalEntity));
    }

    @Override
    public Animal findById(Long id) throws DomainInvalidException, UsuarioNotFoundException, VacinaNotFoundException {
        return animalMapper.fromEntityToDomain(buscarAnimalPorId(id));
    }

    @Override
    public Page<Animal> findAll(FilterModel filter) throws DomainInvalidException {
        logger.info("Buscando todos os clientes no método ClienteGateway::findAll");

        // Realiza a busca paginada dos animals
        Page<AnimalEntity> pageAnimalEntity = animalJpaRepository.findAll(
                buildSpecification(filter),
                filter.toPageable());

        // Mapeia os resultados para objetos Animal usando expressões lambda e streams
        List<Animal> animais = pageAnimalEntity.getContent().stream()
                .map(animalEntity -> {
                    try {
                        return animalMapper.fromEntityToDomain(animalEntity);
                    } catch (Exception ex) {
                        // Log a exceção e retorne null ou um objeto Animal de fallback
                        logger.error("Erro ao converter animalEntity para Animal", ex);
                        return null;
                    }
                })
                .filter(Objects::nonNull) // Filtra elementos não nulos (caso ocorra alguma exceção)
                .toList();

        logger.info("Busca de todos os animais finalizada no método AnimalGateway::findAll");

        // Retorna os resultados mapeados como uma Page
        return new PageImpl<>(animais, pageAnimalEntity.getPageable(), pageAnimalEntity.getTotalElements());
    }

    @Transactional
    @Override
    public Animal update(Long id, Animal source)
            throws UsuarioNotFoundException, UsuarioExistsException, DomainInvalidException {
        var animalEntity = buscarAnimalPorId(id);
        clienteJpaRepository.findByAnimal(id).orElseThrow(() -> new UsuarioExistsException("O cliente associado ao animal não foi encontrado"));
        animalEntity.setDataNascimento(source.getDataNascimento());
        animalEntity.setNome(source.getNome());
        animalEntity.setRaca(source.getRaca());
        
        return animalMapper.fromEntityToDomain(animalJpaRepository.save(animalEntity));
    }

    @Transactional
    @Override
    public boolean delete(Long id) throws UsuarioExistsException {
        logger.info("Entrando no método AnimalGateway::delete com id " + id);
        try {
            buscarAnimalPorId(id);
            animalJpaRepository.deleteById(id);
            logger.info("A entidade com id " + id + " foi deletada");
            return true;
        } catch (Exception ex) {
            logger.error("Erro ao excluir a entidade cliente com o id " + id, ex);
            return false;
        }
    }

    @Override
    public Specification<AnimalEntity> buildSpecification(FilterModel filterModel) {
        Specification<AnimalEntity> spec = Specification.where((root, query, cb) -> cb.equal(root.get("deleted"), false));

        for (var equalFilter : filterModel.getEqualFilters())
            spec = spec.and(EntitySpecification.equal(equalFilter, AnimalEntity.class));

        for (var inFilter : filterModel.getInFilters())
            spec = spec.and(EntitySpecification.in(inFilter, AnimalEntity.class));

        return spec;
    }

    public AnimalEntity buscarAnimalPorId(Long id) throws UsuarioNotFoundException {
        logger.info("Entrando no método AnimalGateway::buscarAnimalPorId com id " + id);
        return Optional.ofNullable(animalJpaRepository.findAnimalByIdAndNotDeleted(id))
                .orElseThrow(() -> new AnimalNotFoundException("Animal não encontrado"));
    }
    
}
