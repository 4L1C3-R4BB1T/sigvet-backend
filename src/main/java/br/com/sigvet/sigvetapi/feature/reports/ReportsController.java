package br.com.sigvet.sigvetapi.feature.reports;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigvet.sigvetapi.common.entities.VaccinationEntity;
import br.com.sigvet.sigvetapi.feature.consult.ConsultRepository;
import br.com.sigvet.sigvetapi.feature.vaccination.VaccinationRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportsController {

    private final ConsultRepository consultRepository;

    private final VaccinationRepository vaccinationRepository;

    @GetMapping("/totalbilled")
    public final ResponseEntity<?> get(@RequestBody TotalBilledRequestDTO request) {

        LocalDateTime initialDate = request.initialDate();
        LocalDateTime finalDate = request.finalDate();

        // Se nenhuma data for fornecida, calcula as datas dos últimos 30 dias
        if (initialDate == null && finalDate == null) {
            finalDate = LocalDateTime.now();
            initialDate = finalDate.minusDays(30);
        }

        // A data inicial não pode ser maior que a data final
        if (initialDate.isAfter(finalDate)) {
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body("The initial date can't be greater than the final date");
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

        var response = new TotalBilledResponse(
            totalBilled, (long) consults, (long) vaccinations.size(), initialDate, finalDate
        );

        return ResponseEntity.ok(response);
    }

}
