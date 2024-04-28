package br.com.sigvet.sigvetapi.common;

import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import br.com.sigvet.sigvetapi.common.entities.BaseEntity;
import br.com.sigvet.sigvetapi.common.models.EqualityFilterModel;
import br.com.sigvet.sigvetapi.common.models.InFilterModel;
import jakarta.persistence.criteria.Predicate;

public class EntitySpecification {

    public static <E extends BaseEntity<Long>> Specification<E> in(InFilterModel in, Class<E> clazz) {
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

    public static <E extends BaseEntity<Long>> Specification<E> equal(EqualityFilterModel eq, Class<E> clazz) {
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