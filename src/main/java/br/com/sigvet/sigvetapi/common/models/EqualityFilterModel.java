package br.com.sigvet.sigvetapi.common.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EqualityFilterModel {
    private String column;
    private String value;
    private boolean isEqual;
}
