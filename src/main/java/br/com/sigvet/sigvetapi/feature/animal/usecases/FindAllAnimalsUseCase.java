package br.com.sigvet.sigvetapi.feature.animal.usecases;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.EntitySpecification;
import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import br.com.sigvet.sigvetapi.common.models.FilterModel;
import br.com.sigvet.sigvetapi.common.usecases.FindAllUseCase;
import br.com.sigvet.sigvetapi.feature.animal.AnimalRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAllAnimalsUseCase implements FindAllUseCase<AnimalEntity> {

    private final AnimalRepository repository;
    
    @Override
    public Page<AnimalEntity> execute(FilterModel filter) {
       return repository.findAll(buildSpecification(filter), filter.toPageable());
    }

    private Specification<AnimalEntity> buildSpecification(FilterModel filterModel) {
        Specification<AnimalEntity> spec = Specification.where((root, query, cb) -> cb.equal(root.get("deleted"), false));

        for (var equalFilter : filterModel.getEqualFilters())
            spec = spec.and(EntitySpecification.equal(equalFilter, AnimalEntity.class));

        for (var inFilter : filterModel.getInFilters())
            spec = spec.and(EntitySpecification.in(inFilter, AnimalEntity.class));

        return spec;
    }
}
