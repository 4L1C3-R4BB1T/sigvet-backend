package br.com.sigvet.sigvetapi.feature.user;

import br.com.sigvet.sigvetapi.common.validators.UserPasswordMatch;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@UserPasswordMatch 
public class UserRequestDTO {
    
    @NotBlank(message = "Nome é obrigatório")
    @Size(message = "Nome pode ter no máximo 100 caracteres", max = 100)
    protected String name;

    @NotBlank(message = "Apelido é obrigatório")
    @Size(message = "Apelido pode ter no máximo 100 caracteres", max = 100)
    protected String username;

    @NotBlank(message = "Documento é obrigatório")
    @Size(message = "Documento deve ter no máximo 14 caracteres", max = 14)
    @Pattern(message = "Documento de ser válido", regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$")
    protected String document;

    @NotBlank(message = "Email é obrigatório")
    @Size(message = "Email deve ter no máximo 100 caracteres", max = 100)
    @Email(message = "Email deve ser válido")
    protected String email;

    @NotBlank(message = "Celular é obrigatório")
    @Size(message = "Celular pode ter no máximo 18 caracteres", max = 18)
    protected String phone;

    @Size(message = "Senha pode ter no máximo 100 caracteres", max = 100)
    protected String password;

    
    @Size(message = "Senha de confirmação pode ter no máximo 100 caracteres", max = 100)
    protected String confirmationPassword;

    @Valid
    protected Address address;

    @Schema(name = "Endereço")
    @Getter 
    @Setter 
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Address {

        @NotBlank(message = "Rua é obrigatório")
        @Size(message = "Rua pode ter no máximo 255 caracteres", max = 255)
        private String street;

        @NotBlank(message = "Bairro é obrigatório")
        @Size(message = "Bairro pode ter no máximo 255 caracteres", max = 255)
        private String neighborhood;

        @NotBlank(message = "CEP é obrigatório")
        @Size(message = "CEP pode ter no máximo 9 caracteres", max = 9)
        @Pattern(message = "CEP deve ter um formato válido", regexp = "^\\d{5}-?\\d{3}$")
        private String zipCode;

        @NotNull(message = "Número é obrigatório")
        @PositiveOrZero(message = "Número não pode ser negativo")
        private Integer number;

        @NotNull(message = "Cidade é obrigatório")
        private Long cityId;

    }

}