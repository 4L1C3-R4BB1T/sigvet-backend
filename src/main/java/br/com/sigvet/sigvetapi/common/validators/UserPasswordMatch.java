package br.com.sigvet.sigvetapi.common.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserPasswordMatchValidator.class)
public @interface UserPasswordMatch {
    String message() default "The password does not match the confirmation password";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}