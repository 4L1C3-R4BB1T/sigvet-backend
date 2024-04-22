package br.com.sigvet.sigvetapi.feature.vaccination;

import java.time.LocalDateTime;

public record VaccinationRequestDTO(
    LocalDateTime dateTime,
    Long veterinarianId,
    Long vaccineId,
    Long animalId
) {

   
}
