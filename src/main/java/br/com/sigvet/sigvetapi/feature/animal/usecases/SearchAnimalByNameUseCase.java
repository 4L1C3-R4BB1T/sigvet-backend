package br.com.sigvet.sigvetapi.feature.animal.usecases;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.entities.AnimalEntity;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class SearchAnimalByNameUseCase {

    private final EntityManager entityManager;
    
    public List<AnimalEntity> execute(String name) {
        final var cb = entityManager.getCriteriaBuilder();
        final var query = cb.createQuery(AnimalEntity.class);
        final var root = query.from(AnimalEntity.class);

        query.select(root).where(
            cb.like(
                cb.lower(cb.function("unaccent", String.class, root.get("name"))),
                cb.function("unaccent", String.class, cb.literal("%" + name + "%"))
            )
        );

        return entityManager.createQuery(query).getResultList();
    }
}
