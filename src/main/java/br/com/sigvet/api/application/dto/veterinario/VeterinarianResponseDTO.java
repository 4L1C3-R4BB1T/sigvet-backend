package br.com.sigvet.api.application.dto.veterinario;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.sigvet.api.application.dto.AddressResponseDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record VeterinarianResponseDTO(
        Long id,
        String usuario,
        String email,
        String nome,
        String cpf,
        String crmv,
        String telefone,
        AddressResponseDTO endereco
) {}
