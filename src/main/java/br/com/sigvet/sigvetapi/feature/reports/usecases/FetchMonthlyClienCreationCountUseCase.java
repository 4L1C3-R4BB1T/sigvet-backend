package br.com.sigvet.sigvetapi.feature.reports.usecases;

import static br.com.sigvet.sigvetapi.feature.reports.utils.ReportSQLUtils.MONTHLY_CLIENT_CREATION_COUNT_RESULT_MAPPING_KEY;
import static br.com.sigvet.sigvetapi.feature.reports.utils.ReportSQLUtils.MONTHLY_CLIENT_CREATION_COUNT_SQL;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.feature.reports.dtos.MonthlyClientCreationCountResponseDTO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class FetchMonthlyClienCreationCountUseCase {
    
    private final EntityManager entityManager;
    
    @SuppressWarnings("unchecked")
    public List<MonthlyClientCreationCountResponseDTO> execute() {
        final var query = entityManager.createNativeQuery(MONTHLY_CLIENT_CREATION_COUNT_SQL, MONTHLY_CLIENT_CREATION_COUNT_RESULT_MAPPING_KEY);
        return (List<MonthlyClientCreationCountResponseDTO>) query.getResultList();
    }
}
