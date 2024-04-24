package br.com.sigvet.sigvetapi.feature.reports;

import java.time.LocalDateTime;

public record ReportDateRequestDTO(
    LocalDateTime initialDate,
    LocalDateTime finalDate
) {}
