package br.com.sigvet.sigvetapi.feature.consult.usecases;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.EntitySpecification;
import br.com.sigvet.sigvetapi.common.entities.ConsultEntity;
import br.com.sigvet.sigvetapi.common.models.FilterModel;
import br.com.sigvet.sigvetapi.common.usecases.FindAllUseCase;
import br.com.sigvet.sigvetapi.feature.consult.ConsultRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAllConsultUseCase implements FindAllUseCase<ConsultEntity> {

    private final ConsultRepository repository;

    @Override
    public Page<ConsultEntity> execute(FilterModel filter) {
        return repository.findAll(buildSpecification(filter), filter.toPageable());
    }

    private Specification<ConsultEntity> buildSpecification(FilterModel filterModel) {
        Specification<ConsultEntity> spec = Specification
                .where((root, query, cb) -> cb.equal(root.get("deleted"), false));

        for (var equalFilter : filterModel.getEqualFilters())
            spec = spec.and(EntitySpecification.equal(equalFilter, ConsultEntity.class));

        for (var inFilter : filterModel.getInFilters())
            spec = spec.and(EntitySpecification.in(inFilter, ConsultEntity.class));

        return spec;
    }

}
