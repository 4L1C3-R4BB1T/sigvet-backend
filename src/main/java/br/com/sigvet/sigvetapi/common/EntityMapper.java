package br.com.sigvet.sigvetapi.common;

import br.com.sigvet.sigvetapi.common.entities.BaseEntity;

public interface EntityMapper<M, E extends BaseEntity<Long>> {

    E fromModel(M source);
}
