package br.com.sigvet.sigvetapi.feature.reports.usecases;

import static br.com.sigvet.sigvetapi.feature.reports.utils.ReportSQLUtils.MONTHLY_ANIMAL_CREATION_COUNT_RESULT_MAPPING_KEY;
import static br.com.sigvet.sigvetapi.feature.reports.utils.ReportSQLUtils.MONTHLY_ANIMAL_CREATION_COUNT_SQL;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.feature.reports.dtos.MonthlyAnimalCreationCountResponseDTO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class FetchMonthlyAnimalCreationCountUseCase {

    private final EntityManager entityManager;
    
    @SuppressWarnings("unchecked")
    public List<MonthlyAnimalCreationCountResponseDTO> execute() {
        final var query = entityManager.createNativeQuery(MONTHLY_ANIMAL_CREATION_COUNT_SQL, MONTHLY_ANIMAL_CREATION_COUNT_RESULT_MAPPING_KEY);
        return (List<MonthlyAnimalCreationCountResponseDTO>) query.getResultList();
    }
}
