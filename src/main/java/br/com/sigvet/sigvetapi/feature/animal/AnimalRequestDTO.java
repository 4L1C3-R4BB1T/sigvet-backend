package br.com.sigvet.sigvetapi.feature.animal;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(name = "Animal", example = "{\"name\":\"Buddy\",\"breed\":\"Labrador Retriever\",\"birthDate\":\"2022-01-15\",\"clientId\":1}")
public record AnimalRequestDTO(

    @NotBlank(message = "Nome é obrigatório") 
    @Size(message = "Nome deve ter no máximo 255 caracteres", max = 255) 
    String name,

    @NotBlank(message = "Raça não pode ser em branco")  
    @Size(message = "Raça deve ter no máximo 255 caracteres", max = 255) 
    String breed,

    @NotNull(message = "Data de Nascimento é obrigatório") 
    LocalDate birthDate,

    @NotNull(message = "Cliente é obrigatório") 
    Long clientId

) {}
