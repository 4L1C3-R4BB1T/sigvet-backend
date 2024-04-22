package br.com.sigvet.sigvetapi.feature.animal;

import java.time.LocalDate;

public record AnimalRequestDTO(
    String name,
    String breed,
    LocalDate birthDate,
    Long clientId
) {
    
}
