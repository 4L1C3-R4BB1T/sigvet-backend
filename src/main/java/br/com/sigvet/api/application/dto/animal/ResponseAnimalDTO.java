package br.com.sigvet.api.application.dto.animal;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseAnimalDTO (
    String nome,
    String raca,
    LocalDate dataNascimento,
    ResponseClienteAnimalDTO cliente
) {
    
}
