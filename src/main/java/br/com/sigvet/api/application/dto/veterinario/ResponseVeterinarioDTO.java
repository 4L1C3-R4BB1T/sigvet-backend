package br.com.sigvet.api.application.dto.veterinario;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.sigvet.api.application.dto.ResponseEnderecoDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ResponseVeterinarioDTO(
        Long id,
        String usuario,
        String email,
        String nome,
        String cpf,
        String crmv,
        String telefone,
        ResponseEnderecoDTO endereco
) {}
