package br.com.sigvet.api.application.dto.cliente;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.sigvet.api.application.dto.AddressResponseDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClientResponseDTO(
        Long id,
        String usuario,
        String email,
        String nome,
        String photo,
        String cpf,
        String telefone,
        AddressResponseDTO endereco,
        List<ClientAnimalResponseDTO> animais
) {}
