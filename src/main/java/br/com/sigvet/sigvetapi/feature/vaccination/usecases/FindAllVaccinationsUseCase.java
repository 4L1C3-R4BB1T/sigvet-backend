package br.com.sigvet.sigvetapi.feature.vaccination.usecases;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.EntitySpecification;
import br.com.sigvet.sigvetapi.common.entities.VaccinationEntity;
import br.com.sigvet.sigvetapi.common.models.FilterModel;
import br.com.sigvet.sigvetapi.common.usecases.FindAllUseCase;
import br.com.sigvet.sigvetapi.feature.vaccination.VaccinationRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAllVaccinationsUseCase implements FindAllUseCase<VaccinationEntity> {

    private final VaccinationRepository repository;

    @Override
    public Page<VaccinationEntity> execute(FilterModel filter) {
        return repository.findAll(buildSpecification(filter), filter.toPageable());
    }

    private Specification<VaccinationEntity> buildSpecification(FilterModel filterModel) {
        Specification<VaccinationEntity> spec = Specification
                .where((root, query, cb) -> cb.equal(root.get("deleted"), false));

        for (var equalFilter : filterModel.getEqualFilters())
            spec = spec.and(EntitySpecification.equal(equalFilter, VaccinationEntity.class));

        for (var inFilter : filterModel.getInFilters())
            spec = spec.and(EntitySpecification.in(inFilter, VaccinationEntity.class));

        return spec;
    }

}
