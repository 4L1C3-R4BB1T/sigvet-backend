package br.com.sigvet.api.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(example = "{\"usuario\":\"john_doe\",\"nome\":\"John Doe\",\"senha\":\"senha123\",\"email\":\"john@example.com\",\"cpf\":\"123.456.789-00\",\"telefone\":\"+55 11 98765-4321\",\"rua\":\"Rua Principal\",\"bairro\":\"Centro\",\"cep\":\"12345-678\",\"numero\":123,\"cidadeId\":1}")
public record CriarClienteDTO(

    @NotBlank(message = "O usuário não pode ser nulo ou vazio")
    String usuario,

    String nome,
    
    @NotBlank(message = "A senha não pode ser nula ou vazia")
    String senha,

    @Email(message = "O e-mail é inválido")
    String email,
    String cpf,
    String telefone,
    String rua,
    String bairro,
    String cep,
    Integer numero,
    Long cidadeId
) {}
