package br.com.sigvet.api.application.dto.animal;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(example = "{\"nome\":\"Nome do animal\",\"raca\":\"Raça do animal\",\"dataNascimento\":\"2022-04-06\",\"clienteId\":1}")
public record RequestCriarAnimalDTO(

    @NotBlank(message = "animal.nome é obrigatório")
    @Size(max = 255, message = "animal.nome pode ter no máximo 255 caracteres")
    String nome,

    @Size(max = 255, message = "animal.raca pode ter no máximo 255 caracteres")
    String raca,

    @NotNull(message = "animal.dataNascimento é obrigatório")
    LocalDate dataNascimento,

    @NotNull(message = "animal.clienteId é obrigatório")
    Long clienteId
) {
    
}
