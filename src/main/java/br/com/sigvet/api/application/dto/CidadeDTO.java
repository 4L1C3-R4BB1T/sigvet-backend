package br.com.sigvet.api.application.dto;

public record CidadeDTO(
    Long id,
    String nome,
    String estado
) {}