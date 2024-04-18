package br.com.sigvet.api.application.mapper.vacinacao;

import java.util.Optional;

import org.springframework.stereotype.Component;

import br.com.sigvet.api.application.dto.vacinacao.AgendarVacinacaoRequestDTO;
import br.com.sigvet.api.application.exception.AnimalNotFoundException;
import br.com.sigvet.api.application.exception.CidadeNotFoundException;
import br.com.sigvet.api.application.exception.UsuarioNotFoundException;
import br.com.sigvet.api.application.exception.VacinaNotFoundException;
import br.com.sigvet.api.application.mapper.animal.AnimalMapper;
import br.com.sigvet.api.application.mapper.base.IVacinacaoMapper;
import br.com.sigvet.api.application.mapper.vacina.VacinaMapper;
import br.com.sigvet.api.application.mapper.veterinario.VeterinarioMapper;
import br.com.sigvet.api.core.domain.entities.Vacinacao;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.infrastructure.entity.VacinacaoEntity;
import br.com.sigvet.api.infrastructure.repository.AnimalJpaRepository;
import br.com.sigvet.api.infrastructure.repository.VacinaJpaRepository;
import br.com.sigvet.api.infrastructure.repository.VeterinarioJpaRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class VacinacaoMapper implements IVacinacaoMapper {

    private final AnimalMapper animalMapper;

    private final VeterinarioMapper veterinarioMapper;

    private final VacinaMapper vacinaMapper;

    private final VeterinarioJpaRepository veterinarioJpaRepository;

    private final VacinaJpaRepository vacinaJpaRepository;

    private final AnimalJpaRepository animalJpaRepository;

    @Override
    public VacinacaoEntity fromDomainToEntity(Vacinacao source) {
        return VacinacaoEntity.builder()
            .animal(animalMapper.fromDomainToEntity(source.getAnimal()))
            .veterinario(veterinarioMapper.fromDomainToEntity(source.getVeterinario()))
            .vacina(vacinaMapper.fromDomainToEntity(source.getVacina()))
            .dataHorario(source.getDataHorario())
            .updatedAt(source.getUpdatedAt())
            .createdAt(source.getCreatedAt())
            .deleted(false)
            .build();
    }

    @Override
    public Vacinacao fromEntityToDomain(VacinacaoEntity source) throws DomainInvalidException {
        return new Vacinacao(
            source.getId(), 
            source.getDataHorario(), 
            veterinarioMapper.fromEntityToDomain(source.getVeterinario()), 
            vacinaMapper.fromEntityToDomain(source.getVacina()),
            animalMapper.fromEntityToDomain(source.getAnimal()));
    }

    @Override
    public Vacinacao fromCriarModelToDomain(AgendarVacinacaoRequestDTO source) throws DomainInvalidException, CidadeNotFoundException {
        var animalEntity = Optional.ofNullable(animalJpaRepository.findAnimalByIdAndNotDeleted(source.animalId())).orElseThrow(AnimalNotFoundException::new);
        var veterinarioEntity = Optional.ofNullable(veterinarioJpaRepository.findVeterinarioByIdAndNotDeleted(source.veterinarioId())).orElseThrow(UsuarioNotFoundException::new);
        var vacinaEntity = vacinaJpaRepository.findVacinaByIdAndNotDeleted(source.vacinaId()).orElseThrow(VacinaNotFoundException::new);

        return new Vacinacao( 
            source.dataHorario(), 
            veterinarioMapper.fromEntityToDomain(veterinarioEntity), 
            vacinaMapper.fromEntityToDomain(vacinaEntity),
            animalMapper.fromEntityToDomain(animalEntity));
    }

    @Override
    public Vacinacao fromAtualizarModelToDomain(Object source) throws DomainInvalidException, CidadeNotFoundException {
        return null;
    }
    
}
