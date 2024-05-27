package br.com.sigvet.sigvetapi.feature.reports;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sigvet.sigvetapi.common.ApplicationException;
import br.com.sigvet.sigvetapi.common.entities.VaccinationEntity;
import br.com.sigvet.sigvetapi.common.entities.VeterinarianEntity;
import br.com.sigvet.sigvetapi.feature.consult.ConsultRepository;
import br.com.sigvet.sigvetapi.feature.reports.dtos.GeneralMetricsResponseDTO;
import br.com.sigvet.sigvetapi.feature.reports.dtos.MonthlyClientAndAnimalCountResponseDTO;
import br.com.sigvet.sigvetapi.feature.reports.dtos.ReportDateRequestDTO;
import br.com.sigvet.sigvetapi.feature.reports.dtos.TotalBilledResponseDTO;
import br.com.sigvet.sigvetapi.feature.reports.dtos.TotalCountVaccineOfUsesResponseDTO;
import br.com.sigvet.sigvetapi.feature.reports.usecases.FetchGeneralMetricsUseCase;
import br.com.sigvet.sigvetapi.feature.reports.usecases.FetchMonthlyAnimalCreationCountUseCase;
import br.com.sigvet.sigvetapi.feature.reports.usecases.FetchMonthlyClientCreationCountUseCase;
import br.com.sigvet.sigvetapi.feature.reports.usecases.FetchTotalCountVaccineOfUsesUseCase;
import br.com.sigvet.sigvetapi.feature.reports.usecases.MetricsExcelUseCase;
import br.com.sigvet.sigvetapi.feature.reports.usecases.MonthlyAnimalCreationCountExcelUseCase;
import br.com.sigvet.sigvetapi.feature.reports.usecases.MonthlyClientCreationCountExcelUseCase;
import br.com.sigvet.sigvetapi.feature.vaccination.VaccinationRepository;
import br.com.sigvet.sigvetapi.feature.veterinarian.VeterinarianRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
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

    private final FetchGeneralMetricsUseCase fetchGeneralMetricsUseCase;

    private final FetchMonthlyAnimalCreationCountUseCase fetchMonthlyAnimalCreationCountUseCase;

    private final FetchMonthlyClientCreationCountUseCase fetchMonthlyClientCreationCountUseCase;

    private final FetchTotalCountVaccineOfUsesUseCase fetchTotalCountVaccineOfUsesUseCase;

    private final MetricsExcelUseCase metricsExcelUseCase;

    private final MonthlyAnimalCreationCountExcelUseCase monthlyAnimalCreationCountExcelUseCase;
    
    private final MonthlyClientCreationCountExcelUseCase monthlyClientCreationCountExcelUseCase;

    @Operation(description = "Obter relatório excel de métricas gerais")
    @GetMapping("/general-metrics/excel")
    public ResponseEntity<byte[]> getGeneralMetricsExcel() {
        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "general_metrics.xls");
        final var bytes = metricsExcelUseCase.execute();
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    @Operation(description = "Obter métricas gerais")
    @GetMapping("/general-metrics")
    public ResponseEntity<GeneralMetricsResponseDTO> getGeneralMetrics() {
        return ResponseEntity.ok(fetchGeneralMetricsUseCase.execute());
    }

    @Operation(description = "Obter planilha de métricas relacionadas a quantidade de animais criados por mês")
    @GetMapping("/monthly-metrics/animals/excel")
    public ResponseEntity<byte[]> getMonthlyCreationCountAnimals() {
        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "animals.xls");
        return new ResponseEntity<>(monthlyAnimalCreationCountExcelUseCase.execute(), headers, HttpStatus.OK);
    }

    @Operation(description = "Obter planilha de métricas relacionadas a quantidade de clientes criados por mês")
    @GetMapping("/monthly-metrics/clients/excel")
    public ResponseEntity<byte[]> getMonthlyCreationCountClients() {
        final var headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "clients.xls");
        return new ResponseEntity<>(monthlyClientCreationCountExcelUseCase.execute(), headers, HttpStatus.OK);
    }

    @Operation(description = "Obter quantidade mensal de animais e clientes cridos")
    @GetMapping("/monthly-metrics")
    public ResponseEntity<MonthlyClientAndAnimalCountResponseDTO> getMonthlyAnimalsAndClients() {
        final var response = new MonthlyClientAndAnimalCountResponseDTO(
            fetchMonthlyAnimalCreationCountUseCase.execute(), 
            fetchMonthlyClientCreationCountUseCase.execute()
        );
        return ResponseEntity.ok(response);
    }

    @Operation(description = "Obter a quantidade de vezes que uma vacina foi utlizada")
    @GetMapping("/vaccine-uses/{id}/total-count")
    public ResponseEntity<TotalCountVaccineOfUsesResponseDTO> getTotalVaccineUses(@PathVariable("id") Long id) {
        return ResponseEntity.ok(fetchTotalCountVaccineOfUsesUseCase.execute(id));
    }

    @Operation(summary = "Obter relatório de total faturado")
    @ApiResponses({
        @ApiResponse(content = @Content(
            schema = @Schema(example = "{\"initialDate\":\"2022-04-30\",\"finalDate\": \"2024-05-30\"}")
        )),
        @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content()),
    })
    @GetMapping("/total-billed")
    public final ResponseEntity<TotalBilledResponseDTO> getTotalBilled(@ModelAttribute @Valid ReportDateRequestDTO request) {
        LocalDate initialDate = null;
        LocalDate finalDate = null;

        if (request.initialDate() != null) {
            initialDate = request.initialDate();
        }

        if (request.finalDate() != null) {
            finalDate = request.finalDate();
        }

        log.info("Entering the getTotalBilled method with initial date {} and final date {}", initialDate, finalDate);

        // Se nenhuma data for fornecida, calcula as datas dos últimos 30 dias
        if (initialDate == null && finalDate == null) {
            finalDate = LocalDate.now();
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

        var result = new TotalBilledResponseDTO(
                totalBilled, (long) consults, (long) vaccinations.size(), initialDate, finalDate);

        log.info("Exiting the getTotalBilled method with result: {}", result);

        return ResponseEntity.ok(result);
    }

    @Operation(summary = "Obter relatório de consultas por veterinário")
    @ApiResponses({
        @ApiResponse(content = @Content(
            schema = @Schema(example = "{\"initialDate\":\"2022-04-30\",\"finalDate\": \"2024-05-30\"}")
        )),
        @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content()),
    })
    @GetMapping("/veterinarians/consults")
    public final ResponseEntity<VeterinariansConsultsResponse> getVeterinariansConsults(@ModelAttribute @Valid ReportDateRequestDTO request) {
        LocalDate initialDate = null;
        LocalDate finalDate = null;

        if (request.initialDate() != null) {
            initialDate = request.initialDate();
        }

        if (request.finalDate() != null) {
            finalDate = request.finalDate();
        }

        log.info("Entering the getVeterinariansConsults method with initial date {} and final date {}", initialDate, finalDate);

        // Se nenhuma data for fornecida, calcula as datas dos últimos 30 dias
        if (initialDate == null && finalDate == null) {
            finalDate = LocalDate.now();
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
