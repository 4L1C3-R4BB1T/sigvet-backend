package br.com.sigvet.sigvetapi.feature.reports.dtos;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GeneralMetricsResponseDTO(
        @JsonProperty("totalClientsCurrentMonth")
        Long totalClients,
        Long totalClientsPreviousMonth,
        @JsonProperty("totalAnimalsCurrentMonth")
        Long totalAnimals,
        Long totalAnimalsPreviousMonth,
        @JsonProperty("totalConsultsCurrentMonth")
        Long totalConsults,
        Long totalConsultsPreviousMonth,
        @JsonProperty("totalRevenueCurrentMonth")
        BigDecimal revenue,
        BigDecimal totalRevenuePreviousMonth
) {
}
