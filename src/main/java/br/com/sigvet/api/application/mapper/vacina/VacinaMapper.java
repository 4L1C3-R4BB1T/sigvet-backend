package br.com.sigvet.api.application.mapper.vacina;

import org.springframework.stereotype.Component;

import br.com.sigvet.api.application.dto.vacina.UpdateVaccineRequestDTO;
import br.com.sigvet.api.application.dto.vacina.CreateVaccineRequestDTO;
import br.com.sigvet.api.application.exception.CidadeNotFoundException;
import br.com.sigvet.api.application.mapper.base.IVacinaMapper;
import br.com.sigvet.api.core.domain.entities.Vacina;
import br.com.sigvet.api.core.exception.DomainInvalidException;
import br.com.sigvet.api.infrastructure.entity.VacinaEntity;

@Component
public class VacinaMapper implements IVacinaMapper {

    @Override
    public VacinaEntity fromDomainToEntity(Vacina source) {
        return VacinaEntity.builder()
            .createdAt(source.getCreatedAt())
            .updatedAt(source.getUpdatedAt())
            .dataValidade(source.getDataValidade())
            .deleted(false)
            .estoque(source.getEstoque())
            .fabricante(source.getFabricante())
            .id(source.getId())
            .lote(source.getLote())
            .nome(source.getNome())
            .precoUnitario(source.getPrecoUnitario())
            .build();
    }

    @Override
    public Vacina fromEntityToDomain(VacinaEntity source) throws DomainInvalidException {
        return new Vacina(source.getId(), source.getNome(), source.getFabricante(), source.getLote(), source.getPrecoUnitario(), source.getEstoque(), source.getDataValidade());
    }

    @Override
    public Vacina fromCriarModelToDomain(CreateVaccineRequestDTO source) throws DomainInvalidException, CidadeNotFoundException {
        return new Vacina(source.nome(), source.fabricante(), source.precoUnitario(), source.estoque(), source.dataValidade());
    }

    @Override
    public Vacina fromAtualizarModelToDomain(UpdateVaccineRequestDTO source) throws DomainInvalidException, CidadeNotFoundException {
        return new Vacina(source.nome(), source.fabricante(), source.precoUnitario(), source.estoque(), source.dataValidade());
    }
    
}
