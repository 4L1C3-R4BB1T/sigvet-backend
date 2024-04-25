package br.com.sigvet.sigvetapi.common;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.sigvet.sigvetapi.common.models.ResponseResultModel;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    ResponseEntity<ResponseResultModel<List<String>>> handleApplicationException(final ApplicationException exception) {
        
        final var responseResultModel = ResponseResultModel.<List<String>>builder()
            .title(exception.getMessage())
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .result(exception.getErrors())
            .build();

        return ResponseEntity.badRequest().body(responseResultModel);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        final var errors =  ex.getBindingResult().getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
        final var responseResultModel = ResponseResultModel.<List<String>>builder()
        .title("Invalid Entity")
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .result(errors)
        .build();
        return ResponseEntity.badRequest().body(responseResultModel);
    }

    
}
