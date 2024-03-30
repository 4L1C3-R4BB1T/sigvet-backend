package br.com.sigvet.api.application.dto;

public record EnderecoDTO(
    Long id,
    String rua,
    String bairro,
    String cep,
    Integer numero,
    CidadeDTO cidade
) {}
