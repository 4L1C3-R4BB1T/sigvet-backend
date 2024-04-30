package br.com.sigvet.sigvetapi.feature.consult;

import java.time.LocalDateTime;

import br.com.sigvet.sigvetapi.common.entities.enums.ConsultationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Consulta", example = "{\"dateTime\":\"2024-05-01T10:00:00\",\"veterinarianId\":1,\"animalId\":1,\"status\":\"SCHEDULED\"}")
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
