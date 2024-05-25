package br.com.sigvet.sigvetapi.feature.security;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(name = "Login", example = "{ \"emailOrUsername\": \"email@email.com\", \"password\": \"password\" }")
public record UserAccountRequestDTO (
    @NotBlank(message = "Email or Username can't be blank")
    @Size(message = "Email or Username max size is 100 characters", max = 100)
    String emailOrUsername, 
    
    @NotBlank(message = "Password can't be blank")
    @Size(message = "Password max size is 100 characters", max = 100)
    String password
) {}
