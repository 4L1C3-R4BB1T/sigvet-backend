package br.com.sigvet.sigvetapi.feature.animal;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Schema(name = "Animal", example = "{\"name\":\"Buddy\",\"breed\":\"Labrador Retriever\",\"birthDate\":\"2022-01-15\",\"clientId\":1}")
public record AnimalRequestDTO(

    @NotBlank(message = "Name can't be blank") 
    @Size(message = "Name max size is 255 characters", max = 255) 
    String name,

    @NotBlank(message = "Breed can't be blank") 
    @Size(message = "Breed max size is 255 characters", max = 255) 
    String breed,

    @NotNull(message = "Birthdate can't be blank") 
    LocalDate birthDate,

    @NotNull(message = "Client ID can't be null") 
    Long clientId

) {}
