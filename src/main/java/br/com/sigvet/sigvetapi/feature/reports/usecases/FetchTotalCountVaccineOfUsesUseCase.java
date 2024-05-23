package br.com.sigvet.sigvetapi.feature.reports.usecases;

import static br.com.sigvet.sigvetapi.feature.reports.utils.ReportSQLUtils.TOTAL_COUNT_VACCINE_OF_USES_RESULT_MAPPING_KEY;
import static br.com.sigvet.sigvetapi.feature.reports.utils.ReportSQLUtils.TOTAL_COUNT_VACCINE_OF_USES_SQL;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.feature.reports.dtos.TotalCountVaccineOfUsesResponseDTO;
import br.com.sigvet.sigvetapi.feature.vaccine.VaccineRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class FetchTotalCountVaccineOfUsesUseCase {

    private final EntityManager entityManager;

    private final VaccineRepository vaccineRepository;

    public TotalCountVaccineOfUsesResponseDTO execute(Long vaccineId) {
        vaccineRepository.findById(vaccineId).orElseThrow(() -> new ApplicationException("Report Exception", List.of("Vaccine does not exists")));
        try {
            final var query = entityManager.createNativeQuery(TOTAL_COUNT_VACCINE_OF_USES_SQL, TOTAL_COUNT_VACCINE_OF_USES_RESULT_MAPPING_KEY);
            query.setParameter(1, vaccineId);
            TotalCountVaccineOfUsesResponseDTO result = (TotalCountVaccineOfUsesResponseDTO) query.getSingleResult();
         return result;
        } catch (Exception ex) {
            return new TotalCountVaccineOfUsesResponseDTO(0L);
        }
    }
}
