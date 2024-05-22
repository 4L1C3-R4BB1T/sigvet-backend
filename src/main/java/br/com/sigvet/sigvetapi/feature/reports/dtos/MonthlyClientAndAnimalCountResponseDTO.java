package br.com.sigvet.sigvetapi.feature.reports.dtos;

import java.util.List;

public record MonthlyClientAndAnimalCountResponseDTO(
    List<MonthlyAnimalCreationCountResponseDTO> animalsResult,
    List<MonthlyClientCreationCountResponseDTO> clientsResult
) {
    
}
