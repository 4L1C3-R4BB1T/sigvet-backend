package br.com.sigvet.api.application.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClienteDTO(
        Long id,

        String usuario,

        String email,

        String nome,

        String cpf,

        String telefone,

        EnderecoDTO endereco,
        List<AnimalDTO> animais) {

}
