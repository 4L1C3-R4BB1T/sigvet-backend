package br.com.sigvet.api.application.dto.cliente;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

@Schema(example = "{\"usuario\":\"john_doe\",\"nome\":\"John Doe\",\"senha\":\"senha123\",\"email\":\"john@example.com\",\"cpf\":\"123.456.789-00\",\"telefone\":\"11987654321\",\"rua\":\"Rua Principal\",\"bairro\":\"Centro\",\"cep\":\"12345-678\",\"numero\":123,\"cidade\":\"Vitória\", \"uf\":\"ES\"}")
public record UpdateClientRequestDTO(
    
    @NotBlank
    @Size(max = 100)
    String usuario,

    @NotBlank
    @Size(max = 100)
    String nome,

    @NotBlank
    @Size(max = 100)
    String senha,

    @Email
    @Size(max = 100)
    String email,

    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$")
    @NotBlank
    String cpf,

    @Size(max = 18)
    String telefone,

    @NotBlank
    String rua,

    @NotBlank
    String bairro,

    @Pattern(regexp = "^\\d{5}-\\d{3}$")
    String cep,

    @PositiveOrZero
    Integer numero,

    @NotBlank
    String cidade,

    @NotBlank
    @Pattern(regexp = "(^[A-Z]{2})")
    String uf
) {}
