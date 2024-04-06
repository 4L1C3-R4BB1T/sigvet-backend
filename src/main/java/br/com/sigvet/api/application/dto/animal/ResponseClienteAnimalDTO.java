package br.com.sigvet.api.application.dto.animal;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.sigvet.api.application.dto.ResponseEnderecoDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseClienteAnimalDTO(
        Long id,
        String usuario,
        String email,
        String nome,
        String cpf,
        String telefone,
        ResponseEnderecoDTO endereco
) {}
