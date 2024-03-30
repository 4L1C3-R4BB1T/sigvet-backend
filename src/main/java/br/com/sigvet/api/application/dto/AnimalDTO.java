package br.com.sigvet.api.application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AnimalDTO(
    Long id,
    String nome,
    String raca,
    LocalDate dataNascimento,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
