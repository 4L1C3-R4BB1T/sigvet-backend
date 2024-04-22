package br.com.sigvet.sigvetapi.common;

import br.com.sigvet.sigvetapi.common.entities.BaseEntity;
import br.com.sigvet.sigvetapi.common.models.FilterModel;
import org.springframework.data.domain.Page;

public interface FindAllUseCase<E extends BaseEntity<Long>> {
    Page<E> execute(FilterModel filter);
}
