package br.com.sigvet.sigvetapi.common.usecases;

import br.com.sigvet.sigvetapi.common.entities.BaseEntity;

public interface CreateUseCase<E extends BaseEntity<Long>> {
    E execute(E record);
}
