package br.com.sigvet.sigvetapi.feature.client.usecases;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.sigvet.sigvetapi.common.EntitySpecification;
import br.com.sigvet.sigvetapi.common.entities.ClientEntity;
import br.com.sigvet.sigvetapi.common.entities.enums.EntityType;
import br.com.sigvet.sigvetapi.common.models.FilterModel;
import br.com.sigvet.sigvetapi.common.usecases.FindAllUseCase;
import br.com.sigvet.sigvetapi.feature.client.ClientRepository;
import br.com.sigvet.sigvetapi.feature.photo.usecases.FindPhotoUseCase;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FindAllClientsUseCase implements FindAllUseCase<ClientEntity> {

    private final ClientRepository repository;

    private final FindPhotoUseCase findPhotoUseCase;

    @Override
    public Page<ClientEntity> execute(FilterModel filter) {
        final Page<ClientEntity> oldPage = repository.findAll(buildSpecification(filter), filter.toPageable());
        final var content = oldPage.getContent().stream().map(obj -> {
            try {
                findPhotoUseCase.execute(obj.getId(), EntityType.USER);
                obj.setPhotoUrl(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/photo/user/{id}").buildAndExpand(obj.getId()).toString());
            } catch (Exception ex) {}
            return obj;
        }).toList();
        return new PageImpl<>(content, oldPage.getPageable(), oldPage.getTotalElements());
    }

    private Specification<ClientEntity> buildSpecification(FilterModel filterModel) {
        Specification<ClientEntity> spec = Specification
                .where((root, query, cb) -> cb.equal(root.get("deleted"), false));

        for (var equalFilter : filterModel.getEqualFilters())
            spec = spec.and(EntitySpecification.equal(equalFilter, ClientEntity.class));

        for (var inFilter : filterModel.getInFilters())
            spec = spec.and(EntitySpecification.in(inFilter, ClientEntity.class));

        return spec;
    }

}
