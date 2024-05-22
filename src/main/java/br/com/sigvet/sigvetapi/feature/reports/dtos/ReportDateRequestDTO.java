package br.com.sigvet.sigvetapi.feature.reports.dtos;

import java.sql.Date;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.PastOrPresent;

@Schema(name = "Relatório", example = "{\"initialDate\":\"2022-04-30\",\"finalDate\":\"2024-05-01\"}")
public record ReportDateRequestDTO(
    
    @PastOrPresent(message = "Date Time should be past or present")
    Date initialDate,
    Date finalDate

) {}
