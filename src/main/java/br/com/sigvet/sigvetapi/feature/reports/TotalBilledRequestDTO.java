package br.com.sigvet.sigvetapi.feature.reports;

import java.time.LocalDateTime;

public record TotalBilledRequestDTO(
    LocalDateTime initialDate,
    LocalDateTime finalDate
) {}
