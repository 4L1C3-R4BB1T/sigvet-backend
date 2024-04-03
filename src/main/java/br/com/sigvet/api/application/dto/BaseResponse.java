package br.com.sigvet.api.application.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
public class BaseResponse<T> {
    
    private boolean success;

    private int status;

    private String message;

    private T result;

    private ErrorResponse error;

    public record ErrorResponse(Long status, String message, List<ValidationError> validations) {}

    public record ValidationError(String field, String message) {}
}
