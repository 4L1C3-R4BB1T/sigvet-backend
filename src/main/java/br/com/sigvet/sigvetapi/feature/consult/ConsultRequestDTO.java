package br.com.sigvet.sigvetapi.feature.consult;

import java.time.LocalDateTime;

import br.com.sigvet.sigvetapi.common.entities.enums.ConsultationStatus;

public record ConsultRequestDTO(
    LocalDateTime dateTime,
    Long veterinarianId,
    Long animalId,
    ConsultationStatus status
) {

   
}
