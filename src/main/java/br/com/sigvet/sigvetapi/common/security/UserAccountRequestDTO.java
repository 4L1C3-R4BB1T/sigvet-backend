package br.com.sigvet.sigvetapi.common.security;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(name = "Login", example = "{ \"email\": \"email@email.com\", \"password\": \"password\" }")
public record UserAccountRequestDTO (
    @NotBlank(message = "Email can't be blank")
    @Size(message = "Email max size is 100 characters", max = 100)
    @Email(message = "Email should be valid")
    String email, 
    
    @NotBlank(message = "Password can't be blank")
    @Size(message = "Password max size is 100 characters", max = 100)
    String password
) {}
