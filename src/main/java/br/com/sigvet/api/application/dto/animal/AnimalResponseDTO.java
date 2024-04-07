package br.com.sigvet.api.application.dto.animal;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AnimalResponseDTO (
    Long id,
    String nome,
    String raca,
    LocalDate dataNascimento,
    AnimalClientResponseDTO cliente
) {
    
}
