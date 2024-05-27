package br.com.sigvet.sigvetapi.feature.security;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(name = "Login", example = "{ \"emailOrUsername\": \"email@email.com\", \"password\": \"password\" }")
public record UserAccountRequestDTO (
    @NotBlank(message = "Email ou Apelido são obrigatórios")
    @Size(message = "Email ou Usuário podem ter no máximo 100 caracteres", max = 100)
    String emailOrUsername, 
    
    @NotBlank(message = "A Senha é obrigatória")
    @Size(message = "A Senha pode ter no máximo 100 caracteres", max = 100)
    String password
) {}
