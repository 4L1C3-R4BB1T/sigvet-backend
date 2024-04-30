package br.com.sigvet.sigvetapi.common.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = FileSizeValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FileSize {

	String message() default "Invalid file size";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	String max() default "2MB"; 
}
