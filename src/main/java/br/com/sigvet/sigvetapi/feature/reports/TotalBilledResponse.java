package br.com.sigvet.sigvetapi.feature.reports;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
public class TotalBilledResponse {

    BigDecimal totalBilled;
    Long consults;
    Long vaccinations;
    @JsonFormat(pattern = "YYYY-MM-dd")
    LocalDateTime initialDate;
    @JsonFormat(pattern = "YYYY-MM-dd")
    LocalDateTime finalDate;

}
