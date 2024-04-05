package br.com.sigvet.api.application.dto.veterinario;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Tag(name = "AtualizarVeterinarioDTO", description = "Operação de atualizar o veterinario")
@Schema(example = "{\"usuario\":\"joao123\",\"nome\":\"João da Silva\",\"senha\":\"senha123\",\"email\":\"joao@example.com\",\"cpf\":\"123.456.789-01\",\"telefone\":\"(12) 3456-7890\"}")
public record AtualizarVeterinarioDTO(
    @NotBlank(message = "veterinario.usuario  é obrigatório")
    String usuario,

    @NotBlank(message = "veterinario.nome  é obrigatório")
    String nome,
    
    @NotBlank(message = "veterinario.senha não pode ser nula ou vazia")
    @Size(max = 100, message = "veterinario.senha não pode ser maior que 100 caracteres")
    String senha,

    @Email(message = "O veterinario.email precisa ter um valor válido")
    String email,

    @NotBlank(message = "veterinario.crmv é obrigatório")
    String crmv,

    @NotBlank(message = "veterinario.crmvUf é obrigatório")
    String crmvUf,

    @NotBlank(message = "veterinario.especialidade é obrigatório")
    String especialidade,

    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "veterinario.cpf precisa estar no formato ddd.ddd.ddd-dd")
    String cpf,

    @Size(max = 18, message = "veterinario.telefone não pode ser maior que 18 caracteres")
    String telefone
) {}
