package br.com.sigvet.sigvetapi.feature.reports;

import java.math.BigDecimal;
import java.time.LocalDateTime;

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
    LocalDateTime initialDate;
    LocalDateTime finalDate;

}
