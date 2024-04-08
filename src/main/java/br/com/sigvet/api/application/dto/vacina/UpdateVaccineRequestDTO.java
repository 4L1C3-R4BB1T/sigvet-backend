package br.com.sigvet.api.application.dto.vacina;

import java.math.BigDecimal;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;


@Schema(example = "{\"nome\":\"NomeDaVacina\",\"fabricante\":\"FabricanteDaVacina\",\"precoUnitario\":10.50,\"estoque\":100,\"dataValidade\":\"2024-12-31\"}")
public record UpdateVaccineRequestDTO(
    @NotBlank
    @Size(max = 255)
    String nome,

    @NotBlank
    @Size(max = 255)
    String fabricante,

    @NotNull
    @PositiveOrZero
    BigDecimal precoUnitario,

    @NotNull 
    @PositiveOrZero
    Integer estoque,

    @NotNull
    @FutureOrPresent
    LocalDate dataValidade
) {}
