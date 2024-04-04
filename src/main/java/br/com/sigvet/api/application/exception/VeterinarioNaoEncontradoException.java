package br.com.sigvet.api.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class VeterinarioNaoEncontradoException extends Exception {

    public VeterinarioNaoEncontradoException(String message) {
        super(message);
    }
}
