package br.com.sigvet.api.application.dto.animal;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(example = "{\"nome\":\"Nome do animal\",\"raca\":\"Raça do animal\",\"dataNascimento\":\"2022-04-06\",\"clienteId\":1}")
public record CreateAnimalRequestDTO(

    @NotBlank
    @Size(max = 255)
    String nome,

    @Size(max = 255)
    String raca,

    @NotNull
    LocalDate dataNascimento,

    @NotNull
    Long clienteId
) {
    
}
