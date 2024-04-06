package br.com.sigvet.api.application.dto.animal;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RequestAtualizarAnimalDTO (
    @NotBlank(message = "animal.nome é obrigatório")
    @Size(max = 255, message = "animal.nome pode ter no máximo 255 caracteres")
    String nome,

    @Size(max = 255, message = "animal.raca pode ter no máximo 255 caracteres")
    String raca,

    @NotNull(message = "animal.dataNascimento é obrigatório")
    LocalDate dataNascimento
) {
    
}
