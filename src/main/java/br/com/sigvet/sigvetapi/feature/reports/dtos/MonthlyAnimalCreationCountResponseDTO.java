package br.com.sigvet.sigvetapi.feature.reports.dtos;

public record MonthlyAnimalCreationCountResponseDTO(
    Integer month,
    Long count
) {
    
}
