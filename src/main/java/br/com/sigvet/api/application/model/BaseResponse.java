package br.com.sigvet.api.application.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    private boolean success;
    private int status;
    private String message;
    private T result;
    private ErrorResponse error;

    public BaseResponse(boolean success, int status, String message, T result) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public BaseResponse(boolean success, int status, String message, ErrorResponse error) {
        this.success = success;
        this.status = status;
        this.message = message;
        this.error = error;
    }

    public record ErrorResponse(List<ValidationError> validations) {}
    public record ValidationError(String field, String message) {}
}
