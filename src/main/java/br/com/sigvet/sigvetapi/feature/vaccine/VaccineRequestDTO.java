package br.com.sigvet.sigvetapi.feature.vaccine;

import java.math.BigDecimal;
import java.time.LocalDate;

public record VaccineRequestDTO(
    String name,
    String manufacturer,
    String lot,
    Integer stock,
    BigDecimal unitPrice,
    LocalDate expirationDate
) {

   
}
