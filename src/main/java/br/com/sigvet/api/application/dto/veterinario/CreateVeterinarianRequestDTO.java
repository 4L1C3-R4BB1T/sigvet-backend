package br.com.sigvet.api.application.dto.veterinario;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Tag(name = "CriarVeterinarioDTO", description = "Payload para ser criar uma veterinario")
@Schema(example = "{\"usuario\":\"usuario\",\"nome\":\"nome\",\"senha\":\"senha\",\"email\":\"email@example.com\",\"cpf\":\"123.456.789-00\",\"crmv\":\"ABC123\",\"crmvUf\":\"SP\",\"especialidade\":\"especialidade\",\"telefone\":\"123456789\",\"rua\":\"rua\",\"bairro\":\"bairro\",\"cep\":\"12345-678\",\"numero\":10,\"cidade\":\"cidade\",\"uf\":\"SP\"}")
public record CreateVeterinarianRequestDTO(

    @NotBlank
    String usuario,

    @NotBlank
    String nome,
    
    @NotBlank
    @Size(max = 100)
    String senha,

    @Email
    String email,

    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "veterinario.cpf precisa estar no formato ddd.ddd.ddd-dd")
    String cpf,

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "O veterinario.crmv deve conter apenas letras e números sem acentuação")
    @Size(max = 45)
    String crmv,

    @NotBlank
    @Size(min = 2, max = 2)
    String crmvUf,

    @NotBlank(message = "veterinario.especialidade é obrigatório")
    String especialidade,

    @Size(max = 18, message = "veterinario.telefone não pode ser maior que 18 caracteres")
    String telefone,

    @NotBlank(message = "veterinario.rua é obrigatório")
    String rua,

    @NotBlank(message = "veterinario.bairro é obrigatório")
    String bairro,

    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "veterinario.cep não precisa estar no formato ddddd-dd")
    String cep,

    @Min(value = 0, message = "veterinario.numero não pode ser meno que 0")
    @NotNull(message = "veterinario.numero não é obrigatório")
    Integer numero,

    @NotBlank(message = "veterinario.cidade n é obrigatório")
    String cidade,

    @NotBlank(message = "veterinario.uf é obrigatório")
    @Pattern(regexp = "(^[A-Z]{2})", message = "veterinario.uf precisa ser ter 2 duas letras maiusculas EX: ES")
    String uf
) {}
