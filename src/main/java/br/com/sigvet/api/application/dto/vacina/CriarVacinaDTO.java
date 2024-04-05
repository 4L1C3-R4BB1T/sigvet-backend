package br.com.sigvet.api.application.dto.vacina;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

@Tag(name = "CriarVacinaDTO", description = "Payload para ser criar uma vacina")
public record CriarVacinaDTO(

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
