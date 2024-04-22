package br.com.sigvet.sigvetapi.feature.vaccination;

import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

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
