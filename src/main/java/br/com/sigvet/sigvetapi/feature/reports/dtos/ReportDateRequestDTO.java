package br.com.sigvet.sigvetapi.feature.reports.dtos;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.PastOrPresent;

@Schema(name = "Relatório", example = "{\"initialDate\":\"2022-04-30\",\"finalDate\":\"2024-05-01\"}")
public record ReportDateRequestDTO(
    
    @PastOrPresent(message = "Date Time should be past or present")
    LocalDate initialDate,
    LocalDate finalDate

) {}
