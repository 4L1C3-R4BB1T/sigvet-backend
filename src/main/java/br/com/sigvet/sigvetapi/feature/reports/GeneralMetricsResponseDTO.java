package br.com.sigvet.sigvetapi.feature.reports;

import java.math.BigDecimal;

public record GeneralMetricsResponseDTO (
    Long totalClients,
    Long totalAnimals,
    Long totalConsults,
    BigDecimal revenue  
) {
    
}
  