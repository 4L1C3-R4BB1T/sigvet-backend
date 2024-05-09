package br.com.sigvet.sigvetapi.common.validators;

import java.util.Objects;

import br.com.sigvet.sigvetapi.feature.user.UserRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UserPasswordMatchValidator implements ConstraintValidator<UserPasswordMatch, UserRequestDTO> {

    @Override
    public boolean isValid(UserRequestDTO target, ConstraintValidatorContext context) {
        return Objects.nonNull(target.getPassword()) && Objects.nonNull(target.getConfirmationPassword())
            && target.getPassword().equalsIgnoreCase(target.getConfirmationPassword());
    }
    
}
