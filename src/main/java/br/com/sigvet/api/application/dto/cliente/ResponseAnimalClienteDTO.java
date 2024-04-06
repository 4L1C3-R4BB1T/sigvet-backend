package br.com.sigvet.api.application.dto.cliente;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseAnimalClienteDTO(
    Long id,
    String nome,
    String raca,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate dataNascimento
) {}
