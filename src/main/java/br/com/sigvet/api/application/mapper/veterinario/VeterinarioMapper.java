package br.com.sigvet.api.application.mapper.veterinario;

import org.springframework.stereotype.Component;

import br.com.sigvet.api.application.dto.veterinario.AtualizarVeterinarioDTO;
import br.com.sigvet.api.application.dto.veterinario.CriarVeterinarioDTO;
import br.com.sigvet.api.application.exception.CidadeNaoExistenteException;
import br.com.sigvet.api.application.mapper.CidadeMapper;
import br.com.sigvet.api.application.mapper.EnderecoMapper;
import br.com.sigvet.api.application.mapper.base.IVeterinarioMapper;
import br.com.sigvet.api.core.domain.entities.Documento;
import br.com.sigvet.api.core.domain.entities.Endereco;
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.infrastructure.entity.VeterinarioEntity;
import br.com.sigvet.api.infrastructure.repository.CidadeJpaRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public final class VeterinarioMapper implements IVeterinarioMapper {

    private final EnderecoMapper enderecoMapper;

    private final CidadeMapper cidadeMapper;
    
    private final CidadeJpaRepository cidadeJpaRepository;

    public VeterinarioEntity fromDomainToEntity(Veterinario source) {
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

    public Veterinario fromEntityToDomain(VeterinarioEntity source) throws DomainInvalidException {
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

     public Veterinario fromCriarModelToDomain(CriarVeterinarioDTO source) throws DomainInvalidException, CidadeNaoExistenteException {

        var cidadeEntity = cidadeJpaRepository.findById(source.cidadeId());

        if (cidadeEntity.isEmpty()) {
            throw new CidadeNaoExistenteException("Cidade não encontrada");
        }

        var cidade = cidadeMapper.toCidade(cidadeEntity.get());

        return new Veterinario(
            source.usuario(),
            source.senha(),
            source.email(),
            source.nome(),
            new Documento(source.cpf()),
            source.telefone(),
            new Endereco(source.rua(), source.bairro(), source.cep(), source.numero(), cidade),
            source.especialidade(),
            source.crmv(),
            source.crmvUf()
        );
    }

     public Veterinario fromAtualizarModelToDomain(AtualizarVeterinarioDTO source) throws DomainInvalidException {
        return new Veterinario(
            source.usuario(),
            source.senha(),
            source.email(),
            source.nome(),
            new Documento(source.cpf()),
            source.telefone(),
            source.especialidade(),
            source.crmv(),
            source.crmvUf()
        );

    }
}
