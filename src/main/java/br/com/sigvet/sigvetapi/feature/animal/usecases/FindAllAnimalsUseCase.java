package br.com.sigvet.sigvetapi.feature.animal.usecases;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sigvet.sigvetapi.common.EntitySpecification;
import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import br.com.sigvet.sigvetapi.common.entities.enums.EntityType;
import br.com.sigvet.sigvetapi.common.models.FilterModel;
import br.com.sigvet.sigvetapi.common.usecases.FindAllUseCase;
import br.com.sigvet.sigvetapi.feature.animal.AnimalRepository;
import br.com.sigvet.sigvetapi.feature.photo.usecases.FindPhotoUseCase;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAllAnimalsUseCase implements FindAllUseCase<AnimalEntity> {

    private final AnimalRepository repository;

    private final FindPhotoUseCase findPhotoUseCase;

    @Override
    public Page<AnimalEntity> execute(final FilterModel filter) {
        final Page<AnimalEntity> oldPage = repository.findAll(buildSpecification(filter), filter.toPageable());
        final var content = oldPage.getContent().stream().map(obj -> {
            try {
                findPhotoUseCase.execute(obj.getId(), EntityType.ANIMAL);
                obj.setPhotoUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/photo/animal/{id}").buildAndExpand(obj.getId()).toString());
            } catch (Exception ex) {}
            return obj;
        }).toList();
        return new PageImpl<>(content, oldPage.getPageable(), oldPage.getTotalElements());
    }

    private Specification<AnimalEntity> buildSpecification(final FilterModel filterModel) {
        Specification<AnimalEntity> spec = Specification
                .where((root, query, cb) -> cb.equal(root.get("deleted"), false));

        for (var equalFilter : filterModel.getEqualFilters())
            spec = spec.and(EntitySpecification.equal(equalFilter, AnimalEntity.class));

        for (var inFilter : filterModel.getInFilters())
            spec = spec.and(EntitySpecification.in(inFilter, AnimalEntity.class));

        return spec;
    }

}
