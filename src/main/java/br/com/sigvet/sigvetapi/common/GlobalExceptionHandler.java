package br.com.sigvet.sigvetapi.common;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.sigvet.sigvetapi.common.models.ResponseResultModel;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    ResponseEntity<ResponseResultModel<String>> handleException(final Exception exception) {
        final var responseResultModel = ResponseResultModel.<String>builder()
                .title("Intern error")
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .result("Um erro ocorreu, tente mais tarde")
                .build(); 

        return ResponseEntity.badRequest().body(responseResultModel);
    }
 
    @ExceptionHandler(ApplicationException.class)
    ResponseEntity<ResponseResultModel<?>> handleApplicationException(final ApplicationException exception) {
        if (exception.hasErrors()) {
            final var responseResultModel = ResponseResultModel.<List<String>>builder()
                .title(exception.getTitle())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .result(exception.getErrors())
                .build();
            return ResponseEntity.badRequest().body(responseResultModel);
        } else {
            final var responseResultModel = ResponseResultModel.<String>builder()
            .title(exception.getTitle())
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .result(exception.getMessage())
            .build();
            return ResponseEntity.badRequest().body(responseResultModel);
        }
    }
    
    @ExceptionHandler(AuthenticationException.class)
    ResponseEntity<ResponseResultModel<String>> handleAuthenticationException(final AuthenticationException exception) {
        final var responseResultModel = ResponseResultModel.<String>builder()
                .title("Authentication")
                .statusCode(HttpStatus.UNAUTHORIZED.value())
                .result("Email/Apelido ou senha incorretos")
                .build();
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(responseResultModel);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        final var errors =  ex.getBindingResult().getAllErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList();
        final var responseResultModel = ResponseResultModel.<List<String>>builder()
        .title("Invalid Entity")
        .statusCode(HttpStatus.BAD_REQUEST.value())
        .result(errors)
        .build();
        return ResponseEntity.badRequest().body(responseResultModel);
    }    
}
