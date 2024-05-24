package br.com.sigvet.sigvetapi.feature.veterinarian.usecases;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sigvet.sigvetapi.common.EntitySpecification;
import br.com.sigvet.sigvetapi.common.entities.VeterinarianEntity;
import br.com.sigvet.sigvetapi.common.entities.enums.EntityType;
import br.com.sigvet.sigvetapi.common.models.FilterModel;
import br.com.sigvet.sigvetapi.common.usecases.FindAllUseCase;
import br.com.sigvet.sigvetapi.feature.photo.usecases.FindPhotoUseCase;
import br.com.sigvet.sigvetapi.feature.veterinarian.VeterinarianRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAllVeterinariansUseCase implements FindAllUseCase<VeterinarianEntity> {

    private final VeterinarianRepository repository;
    
    private final FindPhotoUseCase findPhotoUseCase;
    
    @Override
    public Page<VeterinarianEntity> execute(FilterModel filter) {
        final Page<VeterinarianEntity> oldPage = repository.findAll(buildSpecification(filter), filter.toPageable());
        final var content = oldPage.getContent().stream().map(obj -> {
            try {
                findPhotoUseCase.execute(obj.getId(), EntityType.USER);
                obj.setPhotoUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/photo/user/{id}").buildAndExpand(obj.getId()).toString());
            } catch (Exception ex) {}
            return obj;
        }).toList();
        return new PageImpl<>(content, oldPage.getPageable(), oldPage.getTotalElements());
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
