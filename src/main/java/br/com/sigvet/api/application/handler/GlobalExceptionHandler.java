package br.com.sigvet.api.application.handler;


import java.util.List;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.sigvet.api.application.model.BaseResponse;
import br.com.sigvet.api.application.model.BaseResponse.ErrorResponse;
import br.com.sigvet.api.application.model.BaseResponse.ValidationError;
import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;
    
    @Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();

        List<ValidationError> validationErros = bindingResult
            .getFieldErrors()
            .stream()
            .map(fieldError -> {
                String message = messageSource.getMessage(fieldError, Locale.getDefault());
                return new ValidationError(fieldError.getField(), message);
            })
            .toList();
        
        ErrorResponse errorResponse = new ErrorResponse(validationErros);
        BaseResponse<Void> baseResponse = new BaseResponse<>(false, HttpStatus.BAD_REQUEST.value(), "Campos inválidos", errorResponse);
		return ResponseEntity.badRequest().body(baseResponse);
	}
}
