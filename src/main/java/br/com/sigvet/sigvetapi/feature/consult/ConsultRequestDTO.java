package br.com.sigvet.sigvetapi.feature.consult;

import java.time.LocalDateTime;

import br.com.sigvet.sigvetapi.common.entities.enums.ConsultationStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

public record ConsultRequestDTO(

    @NotNull(message = "Date Time can't be null") 
    @FutureOrPresent(message = "Date Time should be future or present")
    LocalDateTime dateTime,

    @NotNull(message = "Veterinarian ID can't be null") 
    Long veterinarianId,

    @NotNull(message = "Animal ID can't be null") 
    Long animalId,

    @NotNull(message = "Status can't be null") 
    ConsultationStatus status

) {}
