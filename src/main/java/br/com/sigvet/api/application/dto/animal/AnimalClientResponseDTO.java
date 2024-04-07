package br.com.sigvet.api.application.dto.animal;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.sigvet.api.application.dto.AddressResponseDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record AnimalClientResponseDTO(
        Long id,
        String usuario,
        String email,
        String nome,
        String cpf,
        String telefone,
        AddressResponseDTO endereco
) {}
