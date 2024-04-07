package br.com.sigvet.api.application.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AddressResponseDTO(
    String rua,
    String bairro,
    String cep,
    Integer numero,
    String cidade,
    String estado
) {}
