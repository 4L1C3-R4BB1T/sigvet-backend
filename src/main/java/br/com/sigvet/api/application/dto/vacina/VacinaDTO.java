package br.com.sigvet.api.application.dto.vacina;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record VacinaDTO(
        String nome,
        String fabricante,
        String lote,
        BigDecimal precoUnitario,
        Integer estoque,
        LocalDate dataValidade
) {}