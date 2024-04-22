package br.com.sigvet.sigvetapi.common.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
public class InFilterModel {
    private String column;
    private List<String> values;
    private boolean isIn;
}
