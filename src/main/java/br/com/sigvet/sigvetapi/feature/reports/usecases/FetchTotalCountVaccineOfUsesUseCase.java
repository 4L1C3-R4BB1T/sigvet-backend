package br.com.sigvet.sigvetapi.feature.reports.usecases;

import static br.com.sigvet.sigvetapi.feature.reports.utils.ReportSQLUtils.TOTAL_COUNT_VACCINE_OF_USES_RESULT_MAPPING_KEY;
import static br.com.sigvet.sigvetapi.feature.reports.utils.ReportSQLUtils.TOTAL_COUNT_VACCINE_OF_USES_SQL;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.feature.reports.dtos.TotalCountVaccineOfUsesResponseDTO;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class FetchTotalCountVaccineOfUsesUseCase {

    private final EntityManager entityManager;

    public TotalCountVaccineOfUsesResponseDTO execute(Long vaccineId) {
        try {
            final var query = entityManager.createNativeQuery(TOTAL_COUNT_VACCINE_OF_USES_SQL, TOTAL_COUNT_VACCINE_OF_USES_RESULT_MAPPING_KEY);
            System.out.println(TOTAL_COUNT_VACCINE_OF_USES_SQL);
            query.setParameter(1, vaccineId);
            TotalCountVaccineOfUsesResponseDTO result = (TotalCountVaccineOfUsesResponseDTO) query.getSingleResult();
         return result;
        } catch (Exception ex) {
            return new TotalCountVaccineOfUsesResponseDTO(0L);
        }
    }
}
