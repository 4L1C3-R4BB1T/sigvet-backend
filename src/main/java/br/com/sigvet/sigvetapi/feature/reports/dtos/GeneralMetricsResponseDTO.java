package br.com.sigvet.sigvetapi.feature.reports.dtos;

import java.math.BigDecimal;

public record GeneralMetricsResponseDTO(
        Long totalClients,
        Long totalAnimals,
        Long totalConsults,
        BigDecimal revenue) {
}
