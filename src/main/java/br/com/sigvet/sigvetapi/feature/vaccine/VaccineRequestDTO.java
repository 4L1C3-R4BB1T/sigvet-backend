package br.com.sigvet.sigvetapi.feature.vaccine;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public record VaccineRequestDTO(

    @NotBlank(message = "Name can't be blank") 
    @Size(message = "Name max size is 255 characters", max = 255) 
    String name,

    @NotBlank(message = "Manufacturer can't be blank") 
    @Size(message = "Manufacturer max size is 255 characters", max = 255)
    String manufacturer,

    @NotBlank(message = "Lot can't be blank") 
    @Size(message = "Lot max size is 255 characters", max = 255)
    String lot,

    @NotNull(message = "Stock can't be null")
    @PositiveOrZero(message = "Stock should be positive or zero")
    Integer stock,

    @NotNull(message = "Unit Price can't be null") 
    @PositiveOrZero(message = "Unit Price should be positive or zero")
    BigDecimal unitPrice,

    @NotNull(message = "Expiration Date can't be null") 
    @FutureOrPresent(message = "Expiration Date should be future or present")
    LocalDate expirationDate

) {}
