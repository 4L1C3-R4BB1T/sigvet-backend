package br.com.sigvet.sigvetapi.feature.reports.usecases;

import static br.com.sigvet.sigvetapi.feature.reports.utils.ReportSQLUtils.GENERAL_METRICS_RESULT_MAPPING_KEY;
import static br.com.sigvet.sigvetapi.feature.reports.utils.ReportSQLUtils.GENERAL_METRICS_SQL;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.feature.reports.dtos.GeneralMetricsResponseDTO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Component
public class FetchGeneralMetricsUseCase {
    
    private final EntityManager entityManager;

    public GeneralMetricsResponseDTO execute() {
        try {
            final var query = entityManager.createNativeQuery(GENERAL_METRICS_SQL, GENERAL_METRICS_RESULT_MAPPING_KEY);
            GeneralMetricsResponseDTO result = (GeneralMetricsResponseDTO) query.getSingleResult();
         return result;
        } catch (Exception ex) {
            return new GeneralMetricsResponseDTO(0L, 0L, 0L, BigDecimal.ZERO);
        }
    }
}
