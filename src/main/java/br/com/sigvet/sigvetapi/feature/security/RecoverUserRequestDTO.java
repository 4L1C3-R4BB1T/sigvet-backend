package br.com.sigvet.sigvetapi.feature.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RecoverUserRequestDTO(
    @Email(message = "Email is not valid")
    @NotBlank(message = "Email is required")
    String email,

    @NotBlank(message = "Document can't be blank")
    @Size(message = "Document max size is 14 characters", max = 14)
    @Pattern(message = "Document should be valid", regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$")
    String document,

    @NotBlank(message = "Password is required")
    @Size(message = "Password max size is 100 characters", max = 100)
    String password,
    
    @NotBlank(message = "Confirmation Password is required")
    @Size(message = "Confirmation Password max size is 100 characters", max = 100)
    String confirmationPassword
) {
    
}
