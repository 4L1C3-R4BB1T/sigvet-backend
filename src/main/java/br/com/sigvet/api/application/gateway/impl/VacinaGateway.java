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
import br.com.sigvet.api.application.exception.UsuarioExistsException;
import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.application.exception.VacinaNotFoundException;
import br.com.sigvet.api.application.mapper.vacina.VacinaMapper;
import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.domain.entities.Vacina;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVaccineGateway;
import br.com.sigvet.api.infrastructure.entity.VacinaEntity;
import br.com.sigvet.api.infrastructure.repository.VacinaJpaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VacinaGateway implements IVaccineGateway {

    private final VacinaJpaRepository vacinaJpaRepository;
    private final VacinaMapper vacinaMapper;
    
    @Transactional
    @Override
    public Vacina save(Vacina record) throws DomainInvalidException, UsuarioExistsException {
        var vacinaSaved = vacinaJpaRepository.save(vacinaMapper.fromDomainToEntity(record));
        vacinaSaved.setLote("ALOTE" + vacinaSaved.getId());
        vacinaJpaRepository.save(vacinaSaved);
        return vacinaMapper.fromEntityToDomain(vacinaSaved);
    }

    @Override
    public Vacina findById(Long id) throws DomainInvalidException, UsuarioNotFoundException, VacinaNotFoundException {
       return vacinaMapper.fromEntityToDomain(buscarVacinaPorId(id));
    }

    @Override
    public Page<Vacina> findAll(FilterModel filter) {
        logger.info("Buscando todos os vacinas no método VacinaGateway::findAll");

        // Realiza a busca paginada dos vacinas
        Page<VacinaEntity> pageVacinaEntity = vacinaJpaRepository.findAll(
                buildSpecification(filter),
                filter.toPageable());

        // Mapeia os resultados para objetos Vacina usando expressões lambda e streams
        List<Vacina> vacinas = pageVacinaEntity.getContent().stream()
                .map(vacinaEntity -> {
                    try {
                        return vacinaMapper.fromEntityToDomain(vacinaEntity);
                    } catch (Exception ex) {
                        // Log a exceção e retorne null ou um objeto Vacina de fallback
                        logger.error("Erro ao converter vacinaEntity para Vacina", ex);
                        return null;
                    }
                })
                .filter(Objects::nonNull) // Filtra elementos não nulos (caso ocorra alguma exceção)
                .toList();

        logger.info("Busca de todos os vacinas finalizada no método VacinaGateway::findAll");

        // Retorna os resultados mapeados como uma Page
        return new PageImpl<>(vacinas, pageVacinaEntity.getPageable(), pageVacinaEntity.getTotalElements());
    }

    @Transactional
    @Override
    public Vacina update(Long id, Vacina source) throws DomainInvalidException {
        var vacinaSaved = buscarVacinaPorId(id);

        vacinaSaved.setEstoque(source.getEstoque());
        vacinaSaved.setDataValidade(source.getDataValidade());
        vacinaSaved.setNome(source.getNome());
        vacinaSaved.setFabricante(source.getFabricante());
        vacinaSaved.setPrecoUnitario(source.getPrecoUnitario());

        vacinaJpaRepository.save(vacinaSaved);

        return vacinaMapper.fromEntityToDomain(vacinaSaved);
    }

    @Transactional
    @Override
    public boolean delete(Long id) throws UsuarioExistsException {
        logger.info("Entrando no método ClienteGateway::delete com id " + id);
        try {
            buscarVacinaPorId(id);
            vacinaJpaRepository.deleteById(id);
            logger.info("A entidade com id " + id + " foi deletada");
            return true;
        } catch (Exception ex) {
            logger.error("Erro ao excluir a entidade cliente com o id " + id, ex);
            return false;
        }

    }

    @Override
    public Specification<VacinaEntity> buildSpecification(FilterModel filterModel) {
        Specification<VacinaEntity> spec = Specification.where((root, query, cb) -> cb.equal(root.get("deleted"), false));

        for (var equalFilter : filterModel.getEqualFilters())
            spec = spec.and(EntitySpecification.equal(equalFilter, VacinaEntity.class));

        for (var inFilter : filterModel.getInFilters())
            spec = spec.and(EntitySpecification.in(inFilter, VacinaEntity.class));

        return spec;
    }
    
    public VacinaEntity buscarVacinaPorId(Long id) throws VacinaNotFoundException {
        logger.info("Entrando no método VacinaGateway::buscarVacinaPorId com id " + id);
        return Optional.ofNullable(vacinaJpaRepository.findVacinaByIdAndNotDeleted(id))
                .orElseThrow(() -> new VacinaNotFoundException("Vacina não encontrado"));
    }
}
