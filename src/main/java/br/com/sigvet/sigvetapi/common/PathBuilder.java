package br.com.sigvet.sigvetapi.common;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PathBuilder<T> {

    private static final String DOT = ".";

    private Class<T> superClass;

    public Path<T> get(Root<T> root, String field) {
        if (!field.contains(DOT) && containsField(superClass, field)) {
            return root.get(field);
        } else if (field.contains(DOT)) {
            var fields = field.split(Pattern.quote(DOT));

            if (fields.length != 2)
                return null;

            var field1 = fields[0].trim();
            var field2 = fields[1].trim();

            if (containsField(superClass, field1)) {
                var clazz = superClass.getSuperclass();

                if (Objects.nonNull(clazz)) {
                    Class<?> subClass = getAssociationClass(clazz, field1);
                    if (containsField(subClass, field2)) {
                        return root.get(field1).get(field2);
                    }
                }

                Class<?> subClass = getAssociationClass(superClass, field1);

                if (containsField(subClass, field2)) {
                    return root.get(field1).get(field2);
                }
            }
        }
        return null;
    }

    private Class<?> getAssociationClass(Class<?> clazz, String fieldName) {
        try {
            return clazz.getDeclaredField(fieldName).getType();
        } catch (Exception ex) {
            return null;
        }
    }

    private boolean containsField(Class<?> anyClass, String field) {
        if (Objects.isNull(anyClass))
            return false;

        List<Field> fields = new ArrayList<>();

        fields.addAll(Arrays.asList(anyClass.getDeclaredFields()));

        var superClass = anyClass.getSuperclass();

        if (Objects.nonNull(superClass)) {
            fields.addAll(Arrays.asList(superClass.getDeclaredFields()));
            
            superClass = superClass.getSuperclass();

            if (Objects.nonNull(superClass)) {
                fields.addAll(Arrays.asList(superClass.getDeclaredFields()));
            }
        }

        return fields.stream().anyMatch(f -> f.getName().equals(field));
    }

}