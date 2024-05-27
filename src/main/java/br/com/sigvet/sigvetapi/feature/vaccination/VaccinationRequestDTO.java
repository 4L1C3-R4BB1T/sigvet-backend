package br.com.sigvet.sigvetapi.feature.vaccination;

import java.time.LocalDate;
import java.time.LocalTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

@Schema(name = "Vacinação", example = "{\"date\":\"2024-05-01\",\"hour\":\"10:00\",\"veterinarianId\":1,\"vaccineId\":1,\"animalId\":1}")
public record VaccinationRequestDTO(

    @NotNull(message = "Data é obrigatória")
    @FutureOrPresent(message = "Data deve ser no futuro ou presente")
    LocalDate date,

    @NotNull(message = "Hora é obrigatória")
    LocalTime hour,

    @NotNull(message = "Veterinário é obrigatório") 
    Long veterinarianId,

    @NotNull(message = "Vacina é obrigatório") 
    Long vaccineId,

    @NotNull(message = "Animal é obrigatório") 
    Long animalId
) {}
