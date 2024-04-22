package br.com.sigvet.sigvetapi.feature.client;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.EntitySpecification;
import br.com.sigvet.sigvetapi.common.FindAllUseCase;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import br.com.sigvet.sigvetapi.common.models.FilterModel;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAllClientsUseCase implements FindAllUseCase<ClientEntity> {

    private final ClientRepository repository;
    
    @Override
    public Page<ClientEntity> execute(FilterModel filter) {
       return repository.findAll(buildSpecification(filter), filter.toPageable());
    }

    private Specification<ClientEntity> buildSpecification(FilterModel filterModel) {
        Specification<ClientEntity> spec = Specification.where((root, query, cb) -> cb.equal(root.get("deleted"), false));

        for (var equalFilter : filterModel.getEqualFilters())
            spec = spec.and(EntitySpecification.equal(equalFilter, ClientEntity.class));

        for (var inFilter : filterModel.getInFilters())
            spec = spec.and(EntitySpecification.in(inFilter, ClientEntity.class));

        return spec;
    }
}
