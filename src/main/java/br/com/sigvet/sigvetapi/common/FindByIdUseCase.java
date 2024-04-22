package br.com.sigvet.sigvetapi.common;

import br.com.sigvet.sigvetapi.common.entities.BaseEntity;

public interface FindByIdUseCase<E extends BaseEntity<Long>> {

    E execute(Long id);
}
