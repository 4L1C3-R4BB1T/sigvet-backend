package br.com.sigvet.api.application.dto.vacina;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.sigvet.api.application.dto.EnderecoDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record VacinaDTO(
        Long id,
        String usuario,
        String email,
        String nome,
        String cpf,
        String crmv,
        String telefone,
        EnderecoDTO endereco
) {}
