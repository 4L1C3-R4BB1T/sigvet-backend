package br.com.sigvet.sigvetapi.common;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.sigvet.sigvetapi.common.models.ResponseResultModel;

@RestControllerAdvice
public class GlobalExceptionHandler {


//     @Data
// @Builder
// public class ResponseResultModel<T> {
//     private int statusCode;
//     private boolean success;
//     private T result;
// }

    @ExceptionHandler(ApplicationException.class)
    ResponseEntity<ResponseResultModel<List<String>>> handleApplicationException(final ApplicationException exception) {
        
        final var responseResultModel = ResponseResultModel.<List<String>>builder()
            .title(exception.getMessage())
            .statusCode(HttpStatus.BAD_REQUEST.value())
            .result(exception.getErrors());

        return ResponseEntity.badRequest().body(responseResultModel.build());
    }
    
}
