package br.com.sigvet.api.gateway;

import org.springframework.data.jpa.domain.Specification;

import br.com.sigvet.api.application.model.FilterModel;
import br.com.sigvet.api.core.domain.entities.Veterinario;
import br.com.sigvet.api.infrastructure.entity.VeterinarioEntity;

public interface IVeterinarioGateway extends IBaseGateway<Veterinario> {
    Specification<VeterinarioEntity> buildSpecification(FilterModel filterModel);
}
