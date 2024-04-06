package br.com.sigvet.api.application.builder;

import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import br.com.sigvet.api.application.model.EqualityFilterModel;
import br.com.sigvet.api.application.model.InFilterModel;
import br.com.sigvet.api.infrastructure.entity.BaseEntity;
import jakarta.persistence.criteria.Predicate;

public class EntitySpecification {

    public static <T extends BaseEntity> Specification<T> in(InFilterModel in, Class<T> clazz) {
        return (root, query, cb) -> {
            var pathBuilder = new PathBuilder<>(clazz);
            var path = pathBuilder.get(root, in.getColumn());
            if (Objects.isNull(path))
                return cb.and();
            Predicate predicate = in.isIn() ? path.as(String.class).in(in.getValues())
                    : path.as(String.class).in(in.getValues()).not();
            return predicate;
        };
    }

    public static <T extends BaseEntity> Specification<T> equal(EqualityFilterModel eq, Class<T> clazz) {
        return (root, query, cb) -> {
            var pathBuilder = new PathBuilder<>(clazz);
            var path = pathBuilder.get(root, eq.getColumn());
            if (Objects.isNull(path))
                return cb.and();
            Predicate predicate = eq.isEqual() ? cb.equal(path, eq.getValue()) : cb.notEqual(path, eq.getValue());
            return predicate;
        };
    }

}
