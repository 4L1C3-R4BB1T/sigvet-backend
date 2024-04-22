package br.com.sigvet.sigvetapi.common;

public interface UpdateUseCase<E> {
    void execute(Long id, E source);
}
