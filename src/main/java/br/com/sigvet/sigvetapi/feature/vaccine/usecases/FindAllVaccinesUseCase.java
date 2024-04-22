package br.com.sigvet.sigvetapi.feature.vaccine.usecases;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.EntitySpecification;
import br.com.sigvet.sigvetapi.common.entities.VaccineEntity;
import br.com.sigvet.sigvetapi.common.models.FilterModel;
import br.com.sigvet.sigvetapi.common.usecases.FindAllUseCase;
import br.com.sigvet.sigvetapi.feature.vaccine.VaccineRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAllVaccinesUseCase implements FindAllUseCase<VaccineEntity> {

    private final VaccineRepository repository;

    @Override
    public Page<VaccineEntity> execute(FilterModel filter) {
        return repository.findAll(buildSpecification(filter), filter.toPageable());
    }

    private Specification<VaccineEntity> buildSpecification(FilterModel filterModel) {
        Specification<VaccineEntity> spec = Specification
                .where((root, query, cb) -> cb.equal(root.get("deleted"), false));

        for (var equalFilter : filterModel.getEqualFilters())
            spec = spec.and(EntitySpecification.equal(equalFilter, VaccineEntity.class));

        for (var inFilter : filterModel.getInFilters())
            spec = spec.and(EntitySpecification.in(inFilter, VaccineEntity.class));

        return spec;
    }

}
