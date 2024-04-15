package br.com.sigvet.api.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.NoArgsConstructor;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
@NoArgsConstructor
public class VacinaNotFoundException extends RuntimeException {
    public VacinaNotFoundException(String message) {
        super(message);
    }
}
