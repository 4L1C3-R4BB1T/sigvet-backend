package br.com.sigvet.api.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class UsuarioExistsException extends RuntimeException {
    public UsuarioExistsException(String message) {
        super(message);
    }
}
