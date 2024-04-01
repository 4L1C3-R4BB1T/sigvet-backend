package br.com.sigvet.api.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code =  HttpStatus.CONFLICT)
public class UsuarioExistenteException extends Exception {
    public UsuarioExistenteException(String message) {
        super(message);
    }
}
