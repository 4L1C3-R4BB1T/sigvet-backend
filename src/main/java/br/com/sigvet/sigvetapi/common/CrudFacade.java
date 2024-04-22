package br.com.sigvet.sigvetapi.common;

import java.util.Objects;

import br.com.sigvet.sigvetapi.common.entities.BaseEntity;
import br.com.sigvet.sigvetapi.common.models.FilterModel;
import br.com.sigvet.sigvetapi.common.models.PageModel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public abstract class CrudFacade<E extends BaseEntity<Long>> {

    protected final CreateUseCase<E> createUseCase;
    protected final DeleteUseCase<E> deleteUseCase;
    protected final UpdateUseCase<E> updateUseCase;
    protected final FindAllUseCase<E> findAllUseCase;
    protected final FindByIdUseCase<E> findByIdUseCase;

    public PageModel<E> findAll(final FilterModel filter) {
        return PageModel.createNew(findAllUseCase.execute(Objects.requireNonNull(filter)));
    }

    public E findById(final Long id) {
        return findByIdUseCase.execute(Objects.requireNonNull(id));
    }

    public E create(final E record) {
        System.out.println("OIII");
        return createUseCase.execute(Objects.requireNonNull(record));
    }

    public void update(final Long id, final E source) {
        updateUseCase.execute(Objects.requireNonNull(id), Objects.requireNonNull(source));
    }

    public void delete(final Long id) {
        deleteUseCase.execute(Objects.requireNonNull(id));
    }

}
