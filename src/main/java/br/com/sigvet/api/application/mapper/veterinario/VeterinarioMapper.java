package br.com.sigvet.api.application.mapper.veterinario;

import org.springframework.stereotype.Component;

import br.com.sigvet.api.application.mapper.CidadeMapper;
import br.com.sigvet.api.application.mapper.EnderecoMapper;
import br.com.sigvet.api.core.domain.entities.Documento;
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.infrastructure.entity.VeterinarioEntity;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public final class VeterinarioMapper {

    private final EnderecoMapper enderecoMapper;

    private final CidadeMapper cidadeMapper;

    public VeterinarioEntity toEntity(Veterinario source) {
        VeterinarioEntity veterinarioEntity = VeterinarioEntity.builder()
                .cpf(source.getCpf().getValor())
                .createdAt(source.getCreatedAt())
                .updatedAt(source.getUpdatedAt())
                .email(source.getEmail())
                .id(source.getId())
                .nome(source.getNome())
                .senha(source.getSenha())
                .telefone(source.getTelefone())
                .usuario(source.getUsuario())
                .crmv(source.getCrmv())
                .crmvUf(source.getCrmvUf())
                .especialidade(source.getEspecialidade())
                .deleted(false) // TODO validar isso no futuro, talvez altere o valor já setado no banco
                .build();

        veterinarioEntity.setEndereco(enderecoMapper.toEntity(source.getEndereco(), veterinarioEntity,
                cidadeMapper.toEntity(source.getEndereco().getCidade())));

        return veterinarioEntity;
    }

    public Veterinario toVeterinario(VeterinarioEntity source) throws DomainInvalidException {
        var veterinario = new Veterinario(
            source.getId(), 
            source.getUsuario(), 
            source.getSenha(), 
            source.getEmail(), 
            source.getNome(), 
            new Documento(source.getCpf()), 
            source.getTelefone(), 
            source.getEspecialidade(), 
            source.getCrmv(),
            source.getCrmvUf());

        veterinario.setEndereco(enderecoMapper.toEndereco(source.getEndereco(), veterinario, cidadeMapper.toCidade(source.getEndereco().getCidade())));

        return veterinario;
    }
}
