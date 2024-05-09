package br.com.sigvet.sigvetapi.common.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = FileContetTypeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface FileContentType {

    String message() default "Invalid content type";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

    String[] allowed() default {};

}
