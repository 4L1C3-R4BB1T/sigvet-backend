package br.com.sigvet.sigvetapi.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class ApplicationException extends RuntimeException {

    private List<String> errors = new ArrayList<>();

    public ApplicationException(String message, List<String> errors) {
        this(message);
        this.errors = errors;
    }

    public ApplicationException(String message) {
        super(message, null, true, false);
    }

    public void addError(String errorMessage) {
        if (Objects.isNull(this.errors)) {
            this.errors = new ArrayList<>();
        }
        this.errors.add(errorMessage);
    }

}
