package br.com.sigvet.sigvetapi.feature.reports;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Relatório", example = "{\"initialDate\":\"2022-04-30T00:00:00\",\"finalDate\":\"2024-05-01T00:00:00\"}")
public record ReportDateRequestDTO(
    LocalDateTime initialDate,
    LocalDateTime finalDate
) {}
