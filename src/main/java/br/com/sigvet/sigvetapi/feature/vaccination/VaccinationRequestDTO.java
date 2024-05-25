package br.com.sigvet.sigvetapi.feature.vaccination;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Vacinação", example = "{\"dateTime\":\"2024-05-01T10:00:00\",\"veterinarianId\":1,\"vaccineId\":1,\"animalId\":1}")
public record VaccinationRequestDTO(

    @NotNull(message = "Data é obrigatória") 
    LocalDateTime dateTime,

    @NotNull(message = "Veterinário é obrigatório") 
    Long veterinarianId,

    @NotNull(message = "Vacina é obrigatório") 
    Long vaccineId,

    @NotNull(message = "Animal é obrigatório") 
    Long animalId
) {}
