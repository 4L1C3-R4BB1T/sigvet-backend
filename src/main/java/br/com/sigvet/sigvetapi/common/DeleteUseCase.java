package br.com.sigvet.sigvetapi.common;

public interface DeleteUseCase<E> {
    void execute(Long id);
}
