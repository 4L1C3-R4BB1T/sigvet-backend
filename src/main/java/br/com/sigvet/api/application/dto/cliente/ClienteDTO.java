package br.com.sigvet.api.application.dto.cliente;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.sigvet.api.application.dto.AnimalDTO;
import br.com.sigvet.api.application.dto.EnderecoDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ClienteDTO(
        Long id,
        String usuario,
        String email,
        String nome,
        String cpf,
        String telefone,
        EnderecoDTO endereco,
        List<AnimalDTO> animais
) {}
