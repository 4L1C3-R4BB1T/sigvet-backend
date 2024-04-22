package br.com.sigvet.sigvetapi.feature.veterinarian.usecases;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.EntitySpecification;
import br.com.sigvet.sigvetapi.common.entities.VeterinarianEntity;
import br.com.sigvet.sigvetapi.common.models.FilterModel;
import br.com.sigvet.sigvetapi.common.usecases.FindAllUseCase;
import br.com.sigvet.sigvetapi.feature.veterinarian.VeterinarianRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAllVeterinariansUseCase implements FindAllUseCase<VeterinarianEntity> {

    private final VeterinarianRepository repository;
    
    @Override
    public Page<VeterinarianEntity> execute(FilterModel filter) {
       return repository.findAll(buildSpecification(filter), filter.toPageable());
    }

    private Specification<VeterinarianEntity> buildSpecification(FilterModel filterModel) {
        Specification<VeterinarianEntity> spec = Specification
                .where((root, query, cb) -> cb.equal(root.get("deleted"), false));

        for (var equalFilter : filterModel.getEqualFilters())
            spec = spec.and(EntitySpecification.equal(equalFilter, VeterinarianEntity.class));

        for (var inFilter : filterModel.getInFilters())
            spec = spec.and(EntitySpecification.in(inFilter, VeterinarianEntity.class));

        return spec;
    }
    
}
