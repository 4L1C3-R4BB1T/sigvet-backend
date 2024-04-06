package br.com.sigvet.api.application.dto.cliente;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.sigvet.api.application.dto.ResponseEnderecoDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseClienteDTO(
        Long id,
        String usuario,
        String email,
        String nome,
        String cpf,
        String telefone,
        ResponseEnderecoDTO endereco,
        List<ResponseAnimalClienteDTO> animais
) {}
