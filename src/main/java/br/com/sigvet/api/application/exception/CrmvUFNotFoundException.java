package br.com.sigvet.api.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class CrmvUFNotFoundException extends RuntimeException {
    public CrmvUFNotFoundException(String message) {
        super(message);
    }
}
