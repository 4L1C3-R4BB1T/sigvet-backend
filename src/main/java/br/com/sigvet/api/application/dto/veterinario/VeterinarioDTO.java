package br.com.sigvet.api.application.dto.veterinario;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.sigvet.api.application.dto.EnderecoDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record VeterinarioDTO(
        Long id,
        String usuario,
        String email,
        String nome,
        String cpf,
        String crmv,
        String crmvUf,
        String telefone,
        EnderecoDTO endereco
) {}
