package br.com.sigvet.api.application.gateway.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import static br.com.sigvet.api.infrastructure.utils.Utilities.logger;

import java.util.List;
import java.util.Objects;

import br.com.sigvet.api.application.builder.EntitySpecification;
import br.com.sigvet.api.application.mapper.vacinacao.VacinacaoMapper;
import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.domain.entities.Vacinacao;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.gateway.IVacinacaoGateway;
import br.com.sigvet.api.infrastructure.entity.VacinacaoEntity;
import br.com.sigvet.api.infrastructure.repository.VacinacaoJpaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VacinacaoGateway implements IVacinacaoGateway {

    private final VacinacaoJpaRepository vacinacaoJpaRepository;
    
    private final VacinacaoMapper vacinacaoMapper;

    @Override
    public Vacinacao save(Vacinacao record) throws DomainInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    @Override
    public Vacinacao findById(Long id) throws DomainInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public Page<Vacinacao> findAll(FilterModel filter) throws DomainInvalidException {
        logger.info("Buscando todos os vacinacoes no método ClienteGateway::findAll");

        // Realiza a busca paginada dos vacinacoes
        Page<VacinacaoEntity> pageVacinacaoEntity = vacinacaoJpaRepository.findAll(
                buildSpecification(filter),
                filter.toPageable());

        // Mapeia os resultados para objetos Vacinacao usando expressões lambda e streams
        List<Vacinacao> vacinacoes = pageVacinacaoEntity.getContent().stream()
                .map(vacinacaoEntity -> {
                    try {
                        return vacinacaoMapper.fromEntityToDomain(vacinacaoEntity);
                    } catch (Exception ex) {
                        // Log a exceção e retorne null ou um objeto Vacinacao de fallback
                        logger.error("Erro ao converter clienteEntity para Vacinacao", ex);
                        return null;
                    }
                })
                .filter(Objects::nonNull) // Filtra elementos não nulos (caso ocorra alguma exceção)
                .toList();

        logger.info("Busca de todos os vacinacoes finalizada no método VacinacaoGateway::findAll");

        // Retorna os resultados mapeados como uma Page
        return new PageImpl<>(vacinacoes, pageVacinacaoEntity.getPageable(), pageVacinacaoEntity.getTotalElements());
    }

    @Override
    public Vacinacao update(Long id, Vacinacao source) throws DomainInvalidException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public boolean delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public Specification<VacinacaoEntity> buildSpecification(FilterModel filterModel) {
        Specification<VacinacaoEntity> spec = Specification.where((root, query, cb) -> cb.equal(root.get("deleted"), false));

        for (var equalFilter : filterModel.getEqualFilters())
            spec = spec.and(EntitySpecification.equal(equalFilter, VacinacaoEntity.class));

        for (var inFilter : filterModel.getInFilters())
            spec = spec.and(EntitySpecification.in(inFilter, VacinacaoEntity.class));

        return spec;
    }
    
}
