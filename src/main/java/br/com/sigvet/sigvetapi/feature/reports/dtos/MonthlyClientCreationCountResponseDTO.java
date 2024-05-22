package br.com.sigvet.sigvetapi.feature.reports.dtos;

public record MonthlyClientCreationCountResponseDTO(
    Integer month,
    Long count
) {
    
}
