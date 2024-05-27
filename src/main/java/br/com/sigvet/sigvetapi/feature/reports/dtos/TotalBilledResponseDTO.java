package br.com.sigvet.sigvetapi.feature.reports.dtos;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TotalBilledResponseDTO {

    BigDecimal totalBilled;
    Long consults;
    Long vaccinations;
    @JsonFormat(pattern = "YYYY-MM-dd")
    LocalDate initialDate;
    @JsonFormat(pattern = "YYYY-MM-dd")
    LocalDate finalDate;

}
