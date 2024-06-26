package br.com.sigvet.sigvetapi.common.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ResponseResultModel<T> {
    private String title;
    private int statusCode;
    private boolean success;
    private T result;
}
