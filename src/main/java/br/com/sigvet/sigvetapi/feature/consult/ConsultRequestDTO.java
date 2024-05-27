package br.com.sigvet.sigvetapi.feature.consult;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.sigvet.sigvetapi.common.entities.enums.ConsultationStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Consulta", example = "{\"date\":\"2024-05-01\",\"hour\":\"10:00\",\"veterinarianId\":1,\"animalId\":1,\"status\":\"SCHEDULED\"}")
public record ConsultRequestDTO(

    @NotNull(message = "Data é obrigatória")
    @FutureOrPresent(message = "Data deve ser no futuro ou presente")
    LocalDate date,

    @NotNull(message = "Hora é obrigatória")
    LocalTime hour,

    @NotNull(message = "Veterinarian ID can't be null") 
    Long veterinarianId,

    @NotNull(message = "Animal ID can't be null") 
    Long animalId,

    @NotNull(message = "Status can't be null") 
    ConsultationStatus status

) {}
