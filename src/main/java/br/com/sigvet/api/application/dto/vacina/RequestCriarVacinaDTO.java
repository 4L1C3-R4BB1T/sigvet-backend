package br.com.sigvet.api.application.dto.vacina;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(example = "{\"nome\":\"NomeDaVacina\",\"fabricante\":\"FabricanteDaVacina\",\"precoUnitario\":10.50,\"estoque\":100,\"dataValidade\":\"2024-12-31\"}")
public record RequestCriarVacinaDTO(

    @NotBlank(message = "vacina.nome  é obrigatório")
    @Size(max = 255, message = "vacina.nome não pode ter mais de 255 caracteres")
    String nome,

    @NotBlank(message = "vacina.fabricante  é obrigatório")
    @Size(max = 255, message = "vacina.fabricante não pode ter mais de 255 caracteres")
    String fabricante,

    @NotNull(message = "vacina.precoUnitario não pode ser nulo")
    @Min(value = 0, message = "vacina.precoUnitario não pode ter valor negativo")
    BigDecimal precoUnitario,

    @NotNull(message = "vacina.estoque não pode ser nulo")
    Integer estoque,

    @NotNull(message = "vacina.dataValidade não pode ser nulo")
    @FutureOrPresent(message = "vacina.dataValidade não pode estar no passado")
    LocalDate dataValidade
) {}
