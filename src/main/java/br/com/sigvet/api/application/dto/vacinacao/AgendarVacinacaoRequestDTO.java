package br.com.sigvet.api.application.dto.vacinacao;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

@Schema(example = "{\"dataHorario\": \"2024-04-15T08:30:00\", \"veterinarioId\": 123456789, \"vacinaId\": 987654321, \"animalId\": 555666777888}")
public record AgendarVacinacaoRequestDTO (
    @NotNull
    LocalDateTime dataHorario,

    @NotNull
    Long veterinarioId,

    @NotNull
    Long vacinaId,
    
    @NotNull
    Long animalId
) {
    
}
