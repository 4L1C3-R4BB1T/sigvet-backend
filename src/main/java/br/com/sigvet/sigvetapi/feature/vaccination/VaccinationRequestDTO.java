package br.com.sigvet.sigvetapi.feature.vaccination;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Vacinação", example = "{\"dateTime\":\"2024-05-01T10:00:00\",\"veterinarianId\":1,\"vaccineId\":1,\"animalId\":1}")
public record VaccinationRequestDTO(

    @NotNull(message = "Date Time can't be null") 
    @FutureOrPresent(message = "Date Time should be future or present")
    LocalDateTime dateTime,

    @NotNull(message = "Veterinarian ID can't be null") 
    Long veterinarianId,

    @NotNull(message = "Vaccine ID can't be null") 
    Long vaccineId,

    @NotNull(message = "Animal ID can't be null") 
    Long animalId

) {}
