package br.com.sigvet.sigvetapi.feature.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RecoverUserRequestDTO(
    @Email(message = "O e-mail não é válido")
    @NotBlank(message = "O e-mail é obrigatório")
    String email,

    @NotBlank(message = "O documento não pode estar em branco")
    @Size(message = "O tamanho máximo do documento é de 14 caracteres", max = 14)
    @Pattern(message = "O documento deve ser válido", regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$")
    String document,

    @NotBlank(message = "A senha é obrigatória")
    @Size(message = "O tamanho máximo da senha é de 100 caracteres", max = 100)
    String password,

    @NotBlank(message = "A confirmação de senha é obrigatória")
    @Size(message = "O tamanho máximo da confirmação de senha é de 100 caracteres", max = 100)
    String confirmationPassword
) {
    
}
