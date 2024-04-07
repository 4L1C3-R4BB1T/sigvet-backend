package br.com.sigvet.api.application.dto.cliente;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Schema(example = "{\"usuario\":\"john_doe\",\"nome\":\"John Doe\",\"senha\":\"senha123\",\"email\":\"john@example.com\",\"cpf\":\"123.456.789-00\",\"telefone\":\"11987654321\",\"rua\":\"Rua Principal\",\"bairro\":\"Centro\",\"cep\":\"12345-678\",\"numero\":123,\"cidade\":\"Vitória\", \"uf\":\"ES\"}")
public record CreateClientRequestDTO(

    @NotBlank(message = "cliente.usuario  é obrigatório")
    @Size(max = 100, message = "cliente.usuario deve ter no máximo 100 caracteres")
    String usuario,

    @NotBlank(message = "cliente.nome  é obrigatório")
    @Size(max = 100, message = "cliente.nome deve ter no máximo 100 caracteres")
    String nome,
    
    @NotBlank(message = "cliente.senha não pode ser nula ou vazia")
    @Size(max = 100, message = "cliente.senha não pode ser maior que 100 caracteres")
    String senha,

    @Email(message = "O cliente.email precisa ter um valor válido")
    @Size(max = 100, message = "cliente.email deve ter no máximo 100 caracteres")
    String email,

    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$", message = "cliente.cpf precisa estar no formato ddd.ddd.ddd-dd")
    @NotBlank(message = "cliente.cpf é obrigatório")
    String cpf,

    @Size(max = 18, message = "cliente.telefone não pode ser maior que 18 caracteres")
    String telefone,

    @NotBlank(message = "cliente.rua  é obrigatório")
    String rua,

    @NotBlank(message = "cliente.bairro n é obrigatório")
    String bairro,

    @Pattern(regexp = "^\\d{5}-\\d{3}$", message = "cliente.cep não precisa estar no formato ddddd-dd")
    String cep,

    @Min(value = 0, message = "cliente.numero  é obrigatório")
    Integer numero,

    @NotBlank(message = "cliente.cidade n é obrigatório")
    String cidade,

    @NotBlank(message = "cliente.uf é obrigatório")
    @Pattern(regexp = "(^[A-Z]{2})", message = "cliente.uf precisa ser ter 2 duas letras maiusculas EX: ES")
    String uf
) {}
