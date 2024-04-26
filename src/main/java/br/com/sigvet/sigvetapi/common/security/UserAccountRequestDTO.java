package br.com.sigvet.sigvetapi.common.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserAccountRequestDTO (
    @NotBlank(message = "Email can't be blank")
    @Size(message = "Email max size is 100 characters", max = 100)
    @Email(message = "Email should be valid")
    String email, 
    
    @NotBlank(message = "Password can't be blank")
    @Size(message = "Password max size is 100 characters", max = 100)
    String password
) {}
