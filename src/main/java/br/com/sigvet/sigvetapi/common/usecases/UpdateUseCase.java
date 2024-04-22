package br.com.sigvet.sigvetapi.common.usecases;

public interface UpdateUseCase<E> {
    void execute(Long id, E source);
}
