package br.com.sigvet.api.application.dto.vacina;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Tag(name = "AtualizarVeterinarioDTO", description = "Operação de atualizar o veterinario")
@Schema(example = "{\"usuario\":\"joao123\",\"nome\":\"João da Silva\",\"senha\":\"senha123\",\"email\":\"joao@example.com\",\"cpf\":\"123.456.789-01\",\"telefone\":\"(12) 3456-7890\"}")
public record AtualizarVacinaDTO(
    @NotBlank(message = "vacina.nome  é obrigatório")
    String nome,

    @NotBlank(message = "vacina.fabricante  é obrigatório")
    String fabricante,
    
    @NotBlank(message = "vacina.lote não pode ser nula ou vazia")
    @Size(max = 100, message = "vacina.lote não pode ser maior que 100 caracteres")
    String lote,

    @NotNull(message = "vacina.precoUnitario não pode ser nulo")
    BigDecimal precoUnitario,

    @NotNull(message = "vacina.estoque não pode ser nulo")
    Integer estoque,

    @NotNull(message = "vacina.dataValidade não pode ser nulo")
    @Past(message = "vacina.dataValidade não pode estar no passado")
    LocalDate dataValidade
) {}
