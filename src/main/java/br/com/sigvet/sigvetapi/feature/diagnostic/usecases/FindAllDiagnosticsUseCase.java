package br.com.sigvet.sigvetapi.feature.diagnostic.usecases;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.EntitySpecification;
import br.com.sigvet.sigvetapi.common.entities.DiagnosticEntity;
import br.com.sigvet.sigvetapi.common.models.FilterModel;
import br.com.sigvet.sigvetapi.common.usecases.FindAllUseCase;
import br.com.sigvet.sigvetapi.feature.diagnostic.DiagnosticRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAllDiagnosticsUseCase implements FindAllUseCase<DiagnosticEntity> {

    private final DiagnosticRepository repository;

    @Override
    public Page<DiagnosticEntity> execute(FilterModel filter) {
        return repository.findAll(buildSpecification(filter), filter.toPageable());
    }

    private Specification<DiagnosticEntity> buildSpecification(FilterModel filterModel) {
        Specification<DiagnosticEntity> spec = Specification
                .where((root, query, cb) -> cb.equal(root.get("deleted"), false));

        for (var equalFilter : filterModel.getEqualFilters())
            spec = spec.and(EntitySpecification.equal(equalFilter, DiagnosticEntity.class));

        for (var inFilter : filterModel.getInFilters())
            spec = spec.and(EntitySpecification.in(inFilter, DiagnosticEntity.class));

        return spec;
    }

}
