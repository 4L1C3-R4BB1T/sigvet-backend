package br.com.sigvet.api.application.dto.vacina;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Tag(name = "CriarVacinaDTO", description = "Payload para ser criar uma vacina")
@Schema(example = "{\"nome\":\"NomeDaVacina\",\"fabricante\":\"FabricanteDaVacina\",\"precoUnitario\":10.50,\"estoque\":100,\"dataValidade\":\"2024-12-31\"}")
public record CriarVacinaDTO(

    @NotBlank(message = "vacina.nome  é obrigatório")
    String nome,

    @NotBlank(message = "vacina.fabricante  é obrigatório")
    String fabricante,

    @NotNull(message = "vacina.precoUnitario não pode ser nulo")
    BigDecimal precoUnitario,

    @NotNull(message = "vacina.estoque não pode ser nulo")
    Integer estoque,

    @NotNull(message = "vacina.dataValidade não pode ser nulo")
    @FutureOrPresent(message = "vacina.dataValidade não pode estar no passado")
    LocalDate dataValidade
) {}
