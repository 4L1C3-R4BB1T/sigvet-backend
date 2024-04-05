package br.com.sigvet.api.application.dto.veterinario;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Tag(name = "AtualizarClienteDTO", description = "Operação de atualizar o cliente")
@Schema(example = "{\"usuario\":\"joao123\",\"nome\":\"João da Silva\",\"senha\":\"senha123\",\"email\":\"joao@example.com\",\"cpf\":\"123.456.789-01\",\"telefone\":\"(12) 3456-7890\"}")
public record AtualizarVeterinarioDTO(
    @NotBlank(message = "cliente.usuario  é obrigatório")
    String usuario,

    @NotBlank(message = "cliente.nome  é obrigatório")
    String nome,
    
    @NotBlank(message = "cliente.senha não pode ser nula ou vazia")
    @Size(max = 100, message = "cliente.senha não pode ser maior que 100 caracteres")
    String senha,

    @Email(message = "O cliente.email precisa ter um valor válido")
    String email,

    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "cliente.cpf precisa estar no formato ddd.ddd.ddd-dd")
    String cpf,

    @Size(max = 18, message = "cliente.telefone não pode ser maior que 18 caracteres")
    String telefone
) {}
