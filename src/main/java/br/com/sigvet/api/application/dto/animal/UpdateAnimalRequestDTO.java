package br.com.sigvet.api.application.dto.animal;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UpdateAnimalRequestDTO (
    @NotBlank
    @Size(max = 255)
    String nome,

    @Size(max = 255)
    String raca,

    @NotNull
    LocalDate dataNascimento
) {
    
}
