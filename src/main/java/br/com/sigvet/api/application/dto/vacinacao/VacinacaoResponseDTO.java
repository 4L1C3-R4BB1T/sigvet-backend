package br.com.sigvet.api.application.dto.vacinacao;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.sigvet.api.application.dto.animal.AnimalResponseDTO;
import br.com.sigvet.api.application.dto.vacina.VaccineResponseDTO;
import br.com.sigvet.api.application.dto.veterinario.VeterinarianResponseDTO;

public record VacinacaoResponseDTO (
    Long id,
    LocalDateTime dataHorario,
    @JsonIgnoreProperties({"endereco", "usuario", "email", "telefone"})
    VeterinarianResponseDTO veterinario,
    @JsonIgnoreProperties("cliente")
    AnimalResponseDTO animal,
    @JsonIgnoreProperties({"venceu", "precoUnitario", "estoque", "dataValidade"})
    VaccineResponseDTO vacina
) {

   
    
}
