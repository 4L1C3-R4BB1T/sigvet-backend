package br.com.sigvet.sigvetapi.feature.reports;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.VaccinationEntity;
import br.com.sigvet.sigvetapi.common.entities.VeterinarianEntity;
import br.com.sigvet.sigvetapi.feature.consult.ConsultRepository;
import br.com.sigvet.sigvetapi.feature.vaccination.VaccinationRepository;
import br.com.sigvet.sigvetapi.feature.veterinarian.VeterinarianRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "Relatórios", description = "Agrupa endpoints para se obter relatórios")
@Slf4j
@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportsController {

    private final ConsultRepository consultRepository;
    private final VaccinationRepository vaccinationRepository;
    private final VeterinarianRepository veterinarianRepository;

    @GetMapping("/total-billed")
    public final ResponseEntity<?> getTotalBilled(@RequestBody ReportDateRequestDTO request) {
        LocalDateTime initialDate = request.initialDate();
        LocalDateTime finalDate = request.finalDate();

        log.info("Entering the getTotalBilled method with initial date {} and final date {}", initialDate, finalDate);

        // Se nenhuma data for fornecida, calcula as datas dos últimos 30 dias
        if (initialDate == null && finalDate == null) {
            finalDate = LocalDateTime.now();
            initialDate = finalDate.minusDays(30);
        }

        // A data inicial não pode ser maior que a data final
        if (initialDate.isAfter(finalDate)) {
            throw new ApplicationException("The initial date can't be greater than the final date");
        }

        var totalBilled = BigDecimal.ZERO;

        // Quantidade de consultas
        final var consults = consultRepository.findAllByDataBetween(initialDate, finalDate).size();

        // Valor de cada consulta
        totalBilled = totalBilled.add(BigDecimal.valueOf(consults).multiply(BigDecimal.valueOf(350)));

        // Vacinações
        final var vaccinations = vaccinationRepository.findAllByDataBetween(initialDate, finalDate);

        // Valor de cada vacina + 10 reais da aplicação
        for (VaccinationEntity vaccination : vaccinations) {
            totalBilled = totalBilled.add(vaccination.getVaccine().getUnitPrice().add(BigDecimal.TEN));
        }

        var result = new TotalBilledResponse(
                totalBilled, (long) consults, (long) vaccinations.size(), initialDate, finalDate);

        log.info("Exiting the getTotalBilled method with result: {}", result);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/veterinarians/consults")
    public final ResponseEntity<?> getVeterinariansConsults(@RequestBody ReportDateRequestDTO request) {
        LocalDateTime initialDate = request.initialDate();
        LocalDateTime finalDate = request.finalDate();

        log.info("Entering the getVeterinariansConsults method with initial date {} and final date {}", initialDate, finalDate);

        // Se nenhuma data for fornecida, calcula as datas dos últimos 30 dias
        if (initialDate == null && finalDate == null) {
            finalDate = LocalDateTime.now();
            initialDate = finalDate.minusDays(30);
        }

        // A data inicial não pode ser maior que a data final
        if (initialDate.isAfter(finalDate)) {
            throw new ApplicationException("The initial date can't be greater than the final date");
        }

        List<VeterinarianConsults> veterinariansConsults = new ArrayList<>();

        // Quantidade de consultas por cada veterinário
        for (VeterinarianEntity veterinarian : veterinarianRepository.findAll()) {
            var consults = consultRepository
                    .findAllByVeterinarianAndDataBetween(initialDate, finalDate, veterinarian.getId()).size();

            veterinariansConsults.add(new VeterinarianConsults(veterinarian.getName(),
                    veterinarian.getCrmv(), veterinarian.getCrmvUf(), (long) consults));
        }

        VeterinariansConsultsResponse result = new VeterinariansConsultsResponse(
                veterinariansConsults, initialDate, finalDate);

        log.info("Exiting the getVeterinariansConsults method with result: {}", result);

        return ResponseEntity.ok(result);
    }

}
